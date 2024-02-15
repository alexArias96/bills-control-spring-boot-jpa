package com.bolsadeideas.springboot.app.controllers;

import com.bolsadeideas.springboot.app.models.entity.Bill;
import com.bolsadeideas.springboot.app.models.entity.Customer;
import com.bolsadeideas.springboot.app.models.entity.ItemBill;
import com.bolsadeideas.springboot.app.models.entity.Product;
import com.bolsadeideas.springboot.app.models.service.ICustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/bill")
@SessionAttributes("bill")
public class BillController {

    //Obtain the clientId: customerService inject
    @Autowired
    private ICustomerService iCustomerService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/viewDetailsBill/{id}")
    public String viewDetailsBill(@PathVariable(value = "id") Long id,
                            Model model,
                            RedirectAttributes flash){

        //The first: Obtain bill by id
        Bill bill =  iCustomerService.findBillById(id);

        //Validate if the bill es null?
        if(bill == null){
            flash.addFlashAttribute("error", "The bill does not exist in the database");
            return "redirect:/list";
        }

        //Pasamos la factura a la vista mediante el objeto Model
        model.addAttribute("bill", bill);
        model.addAttribute("title", "Bill: ".concat(bill.getDescription()));

        return "bill/viewDetailsBill";
    }

    @GetMapping("/form/{customerId}")
    public String create(@PathVariable(value = "customerId") Long customerId,
                         Map<String, Object> model,
                         RedirectAttributes flash){

        Customer customer = iCustomerService.findOne(customerId);
        if (customer == null){
            flash.addFlashAttribute("error", "The customer doesn´t exist in the database");
            return "redirect:/list";
        }

        Bill bill = new Bill();
        bill.setCustomer(customer);

        model.put("bill", bill);
        model.put("title", "Create bill");

        return "bill/form";
    }

    @GetMapping(value = "/products-uploads/{term}", produces = {"application/json"})
    public @ResponseBody List<Product> productsUploads(@PathVariable String term){
        return iCustomerService.findByName(term);
    }

    @PostMapping("/form")
    public String saveBill(@Valid Bill bill,
                           BindingResult result,
                           Model model,
                           @RequestParam(name = "item_id[]", required = false) Long[] itemId,
                           @RequestParam(name = "amount[]", required = false) Integer[] amount,
                           RedirectAttributes flash,
                           SessionStatus status){

        if (result.hasErrors()){
            model.addAttribute("title", "Create Bill");
            return "bill/form";
        }

        if (itemId == null || itemId.length == 0){
            model.addAttribute("title", "Create Bill");
            model.addAttribute("error", "Error: The bill cannot be empty");
            return "bill/form";
        }

        for (int i = 0; i < itemId.length; i++) {
            Product product = iCustomerService.findProductById(itemId[i]);

            ItemBill lineBill = new ItemBill();

            lineBill.setAmount(amount[i]);

            lineBill.setProduct(product);

            bill.addItemBill(lineBill);

            logger.info("ID: " + itemId[i].toString() + "amount: " + amount[i].toString());
        }

        iCustomerService.saveBill(bill);

        status.setComplete();

        flash.addFlashAttribute("success", "create bill success!");

        return "redirect:/viewBills/" + bill.getCustomer().getId(); //This is due to the relationship that the invoice has with the entity customer
    }

    @GetMapping("/delete/{id}")
    public String deleteBill(@PathVariable(value = "id") Long id,
                             RedirectAttributes flash){
        Bill bill = iCustomerService.findBillById(id);
        if (bill != null){
            iCustomerService.deleteBill(id);
            flash.addFlashAttribute("success", "Bill successfully deleted");
            return "redirect:/viewBills/" + bill.getCustomer().getId();
        }
        flash.addFlashAttribute("error", "The bill does´t exist in database");
        return "redirect:/list";
    }
}
