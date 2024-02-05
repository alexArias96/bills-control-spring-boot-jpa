package com.bolsadeideas.springboot.app.models.service;

import com.bolsadeideas.springboot.app.models.dao.IBillDao;
import com.bolsadeideas.springboot.app.models.dao.ICustomerDao;
import com.bolsadeideas.springboot.app.models.dao.IProductDAO;
import com.bolsadeideas.springboot.app.models.entity.Bill;
import com.bolsadeideas.springboot.app.models.entity.Customer;
import com.bolsadeideas.springboot.app.models.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private ICustomerDao customerDao;

    @Autowired
    private IProductDAO productDAO;

    @Autowired
    private IBillDao billDao;

    @Override
    @Transactional(readOnly = true)
    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    @Override
    @Transactional
    public void save(Customer customer) {
        customerDao.save(customer);
    }

    @Override
    @Transactional(readOnly = true)
    public Customer findOne(Long id) {
        return customerDao.findOne(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        customerDao.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findByName(String term) {
        //return productDAO.findByName(term);
        return productDAO.findByNameLikeIgnoreCase("%" + term + "%");
    }

    @Override
    @Transactional
    public void saveBill(Bill bill) {
        billDao.save(bill);
    }

    @Override
    @Transactional(readOnly = true)
    public Product findProductById(Long id) {
        return productDAO.findById(id).orElse(null);
    }
}
