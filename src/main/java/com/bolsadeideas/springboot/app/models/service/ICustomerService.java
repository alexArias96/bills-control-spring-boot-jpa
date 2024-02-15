package com.bolsadeideas.springboot.app.models.service;

import com.bolsadeideas.springboot.app.models.entity.Bill;
import com.bolsadeideas.springboot.app.models.entity.Customer;
import com.bolsadeideas.springboot.app.models.entity.Product;

import java.util.List;

public interface ICustomerService {
    public List<Customer> findAll();

    public void save(Customer customer);

    public Customer findOne(Long id);

    public void delete(Long id);

    public List<Product> findByName(String term);

    public void saveBill(Bill bill);

    public Product findProductById(Long id);

    public Bill findBillById(Long id);
    public void deleteBill(Long id);
}