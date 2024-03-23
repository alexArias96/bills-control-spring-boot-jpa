package com.bolsadeideas.springboot.app.view.xml;

import com.bolsadeideas.springboot.app.models.entity.Customer;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

//Step: 2
@XmlRootElement(name = "customers")
public class CustomerList {

    /**
     * You need:
     * Call the Class Customer as Array
     * A constructor with that assigned the list
     * A constructor empty
     * A method get to obtain the customer list
    * */
    @XmlElement(name = "customer")
    public List<Customer> customers;

    public CustomerList() {
    }

    public CustomerList(List<Customer> customers){
        this.customers = customers;
    }

    public List<Customer> getCustomers() {
        return customers;
    }
}
