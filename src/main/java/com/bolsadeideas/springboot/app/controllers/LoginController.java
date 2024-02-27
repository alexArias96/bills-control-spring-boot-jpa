package com.bolsadeideas.springboot.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false)
                        String error,
                        @RequestParam(value = "logout", required = false)
                        String logout,
                        Model model,
                        Principal principal,
                        RedirectAttributes flash) {

        //Evitamos que haga doble inicio de sessión
        if (principal != null) {
            flash.addFlashAttribute("info", "You have previously logged in");
            return "redirect:/";
        }

        if (error != null){
            model.addAttribute("error", "Error: Incorrect username or password. Try again");
        }

        if (logout != null){
            model.addAttribute("success", "Session closed successfully!!");
        }

        return "login";
    }
}
