package com.bolsadeideas.springboot.app.controllers;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import com.bolsadeideas.springboot.app.models.entity.Customer;
import com.bolsadeideas.springboot.app.models.service.IUploadsFileService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.core.io.Resource;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import com.bolsadeideas.springboot.app.models.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@SessionAttributes("customer")
public class CustomerController {

    protected final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private ICustomerService iCustomerService;

    @Autowired
    private IUploadsFileService uploadsFileService;


//    @Secured("ROLE_USER") authorization by annotations
    @GetMapping(value = "/uploads/{filename:.+}")
    public ResponseEntity<Resource> viewPhoto(@PathVariable String filename) throws MalformedURLException {

        Resource resource = uploadsFileService.load(filename);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; " +
                        "filename\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    //@Secured("ROLE_USER", "ROLE_ADMIN")authorization by annotations
    @GetMapping("/viewBills/{id}")
    public String view(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

        Customer customer = iCustomerService.fetchByIdWithBills(id); //iCustomerService.findOne(id);
        if (customer == null) {
            flash.addFlashAttribute("error", "This customer doesn´t exists in database");
            return "redirect:/list";
        }

        model.put("customer", customer);
        model.put("title", "customer details: " + customer.getName());

        return "viewBills";
    }

    @RequestMapping(value = {"/list", "/"}, method = RequestMethod.GET)
    public String list(Model model, Authentication authentication, HttpServletRequest request) {

        if (authentication != null){
            logger.info("The authenticated user is: ".concat(authentication.getName()));
        }

        if (hasRole("ROLE_ADMIN")){
            logger.info("Hi ".concat(authentication.getName()).concat(" you have access"));
        }else {
            logger.info("Hi ".concat(authentication.getName()).concat(" you don´t have access"));
        }

        //Second form checkout authorities ==============================================================================================

        SecurityContextHolderAwareRequestWrapper securityContext = new SecurityContextHolderAwareRequestWrapper(request, "ROLE_");

        if (securityContext.isUserInRole("ADMIN")){
            logger.info("Second form using 'SecurityContextHolderAwareRequestWrapper': Hi ".concat(authentication.getName()).concat(" you have access"));
        }else {
            logger.info("Second form using 'SecurityContextHolderAwareRequestWrapper': Hi ".concat(authentication.getName()).concat(" you don´t have access"));
        }


        //Third form checkout authorities ==============================================================================================
        if (request.isUserInRole("ADMIN")){
            logger.info("Second form using 'HttpServletRequest': Hi ".concat(authentication.getName()).concat(" you have access"));
        }else {
            logger.info("Second form using 'HttpServletRequest': Hi ".concat(authentication.getName()).concat(" you don´t have access"));
        }


        model.addAttribute("title", "Customer List");
        model.addAttribute("customers", iCustomerService.findAll());
        return "list";
    }

//    @Secured("ROLE_ADMIN") authorization by annotations
    @RequestMapping(value = "/form")
    public String create(Map<String, Object> model) {

        Customer customer = new Customer();
        model.put("customer", customer);
        model.put("title", "customers Form");
        return "form";
    }

//    @Secured("ROLE_ADMIN") authorization by annotations
    @RequestMapping(value = "/form/{id}")
    public String edit(@PathVariable(value = "id") Long id, Map<String, Object> model) {

        Customer customer = null;

        if (id > 0) {
            customer = iCustomerService.findOne(id);
            if (customer == null) {
                return "redirect:/list";
            }
        } else {
            return "redirect:/list";
        }
        model.put("customer", customer);
        model.put("title", "Edit Customer");
        return "form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String save(@Valid Customer customer, BindingResult result, Model model, @RequestParam("file") MultipartFile photo, RedirectAttributes flash, SessionStatus status) throws IOException {
        if (result.hasErrors()) {
            model.addAttribute("title", "Customer Form");
            return "form";
        }

        if (!photo.isEmpty()) {

            if (customer.getId() != null
                    && customer.getId() > 0
                    && customer.getPhoto() != null
                    && !customer.getPhoto().isEmpty()) {

                uploadsFileService.delete(customer.getPhoto()); //because that´s how you get file name
            }

            String uniqueFileName = uploadsFileService.copy(photo);

            flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFileName + "'");

            customer.setPhoto(uniqueFileName);
        }

        iCustomerService.save(customer);
        status.setComplete();
        return "redirect:list";
    }

//    @Secured("ROLE_ADMIN") authorization by annotations
    @RequestMapping(value = "/eliminate/{id}")
    public String eliminate(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

        if (id > 0) {
            Customer customer = iCustomerService.findOne(id);
            iCustomerService.delete(id);

            flash.addFlashAttribute("success", "Customer successfully deleted!");

            if (uploadsFileService.delete(customer.getPhoto())) {
                flash.addFlashAttribute("photo", "Photo: " + customer.getPhoto() + " successfully deleted");
            }

        }
        return "redirect:/list";
    }

    private boolean hasRole(String role){
        //Video 485 obteniendo roles programaticamente

        SecurityContext context = SecurityContextHolder.getContext();

        if (context == null){
            return false;
        }

        Authentication auth = context.getAuthentication();

        if (auth == null){
            return false;
        }

        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();

//        for (GrantedAuthority authority: authorities) {
//            if (role.equals(authority.getAuthority())){
//                logger.info("Hi User ".concat(auth.getName()).concat(" your role is: ".concat(authority.getAuthority())));
//                return true;
//            }
//        }
//        return false;

        //El metodo 'contains(GrantedAuthority)' retorna un bool si contiene o no el elemento de la coleccion
        return authorities.contains(new SimpleGrantedAuthority(role));
    }
}