package com.bolsadeideas.springboot.app.models.dao;

import com.bolsadeideas.springboot.app.models.entity.Customer;

import java.util.List;

public interface ICustomerDao {
	
	public List<Customer> findAll();

	public void save(Customer customer);
	
	public Customer findOne(Long id);
	
	public void delete(Long id);

}
