package com.bolsadeideas.springboot.app.controllers;

import com.bolsadeideas.springboot.app.models.service.ICustomerService;
import com.bolsadeideas.springboot.app.view.xml.CustomerList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//540 rest controller
@RestController
@RequestMapping("/api/customers")
public class CustomerRestController {
    @Autowired
    private ICustomerService iCustomerService;
    @GetMapping(value = "/list")
    public CustomerList list() {
        return new CustomerList(iCustomerService.findAll());
    }
}
