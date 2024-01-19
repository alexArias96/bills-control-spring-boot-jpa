package com.bolsadeideas.springboot.app.controllers;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;
import com.bolsadeideas.springboot.app.models.service.IUploadsFileService;
import org.springframework.core.io.Resource;
import javax.validation.Valid;
import com.bolsadeideas.springboot.app.models.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.bolsadeideas.springboot.app.models.entity.Cliente;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@SessionAttributes("cliente")
public class ClienteController {

    @Autowired
    private IClienteService iClienteService;

    @Autowired
    private IUploadsFileService uploadsFileService;


    @GetMapping(value = "/uploads/{filename:.+}")
    public ResponseEntity<Resource> verFoto(@PathVariable String filename) throws MalformedURLException {

        Resource recurso = uploadsFileService.load(filename);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename\"" + recurso.getFilename() + "\"")
                .body(recurso);
    }

    @GetMapping("/ver/{id}")
    public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

        Cliente cliente = iClienteService.findOne(id);
        if (cliente == null) {
            flash.addFlashAttribute("error", "el cliente no exite en la base de datos");
            return "redirect:/listar";
        }

        model.put("cliente", cliente);
        model.put("titulo", "Detalle cliente: " + cliente.getNombre());

        return "ver";
    }

    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public String listar(Model model) {
        model.addAttribute("titulo", "Listado de clientes");
        model.addAttribute("clientes", iClienteService.findAll());
        return "listar";
    }

    @RequestMapping(value = "/form")
    public String crear(Map<String, Object> model) {

        Cliente cliente = new Cliente();
        model.put("cliente", cliente);
        model.put("titulo", "Formulario de Cliente");
        return "form";
    }

    @RequestMapping(value = "/form/{id}")
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {

        Cliente cliente = null;

        if (id > 0) {
            cliente = iClienteService.findOne(id);
        } else {
            return "redirect:/listar";
        }
        model.put("cliente", cliente);
        model.put("titulo", "Editar Cliente");
        return "form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String guardar(@Valid Cliente cliente, BindingResult result, Model model, @RequestParam("file") MultipartFile photo, RedirectAttributes flash) throws IOException {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Cliente");
            return "form";
        }

        if (!photo.isEmpty()) {

            if (cliente.getId() != null
                    && cliente.getId() > 0
                    && cliente.getPhoto() != null
                    && !cliente.getPhoto().isEmpty()) {

                uploadsFileService.delete(cliente.getPhoto()); //because that´s how you get file name
            }

            String uniqueFileName = uploadsFileService.copy(photo);

            flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFileName + "'");

            cliente.setPhoto(uniqueFileName);
        }

        iClienteService.save(cliente);
        return "redirect:listar";
    }

    @RequestMapping(value = "/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

        if (id > 0) {
            Cliente cliente = iClienteService.findOne(id);
            iClienteService.delete(id);

            flash.addFlashAttribute("success", "Cliente eliminado con exito!");

            if (uploadsFileService.delete(cliente.getPhoto())) {
                flash.addFlashAttribute("photo", "Photo: " + cliente.getPhoto() + " eliminado con exito");
            }

        }
        return "redirect:/listar";
    }
}