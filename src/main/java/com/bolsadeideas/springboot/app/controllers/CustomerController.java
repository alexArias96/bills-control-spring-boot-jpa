package com.bolsadeideas.springboot.app.controllers;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

import com.bolsadeideas.springboot.app.models.entity.Customer;
import com.bolsadeideas.springboot.app.models.service.IUploadsFileService;
import org.springframework.core.io.Resource;
import javax.validation.Valid;
import com.bolsadeideas.springboot.app.models.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private ICustomerService iCustomerService;

    @Autowired
    private IUploadsFileService uploadsFileService;


    @GetMapping(value = "/uploads/{filename:.+}")
    public ResponseEntity<Resource> verFoto(@PathVariable String filename) throws MalformedURLException {

        Resource resource = uploadsFileService.load(filename);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping("/viewBills/{id}")
    public String viewBills(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

        Customer customer = iCustomerService.findOne(id);
        if (customer == null) {
            flash.addFlashAttribute("error", "This customer doesn´t exists in database");
            return "redirect:/list";
        }

        model.put("customer", customer);
        model.put("title", "customer details: " + customer.getName());

        return "viewBills";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("title", "customer List");
        model.addAttribute("customers", iCustomerService.findAll());
        return "list";
    }

    @RequestMapping(value = "/form")
    public String create(Map<String, Object> model) {

        Customer customer = new Customer();
        model.put("customer", customer);
        model.put("title", "customers Form");
        return "form";
    }

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
        model.put("title", "Edit customer");
        return "form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String saveCustomer(@Valid Customer customer, BindingResult result, Model model, @RequestParam("file") MultipartFile photo, RedirectAttributes flash, SessionStatus status) throws IOException {
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
}