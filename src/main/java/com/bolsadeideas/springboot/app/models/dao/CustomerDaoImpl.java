package com.bolsadeideas.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.bolsadeideas.springboot.app.models.entity.Customer;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDaoImpl implements ICustomerDao {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> findAll() {
		return em.createQuery("from Customer").getResultList();
	}

	@Override
	public Customer findOne(Long id) {
		return em.find(Customer.class, id);
	}

	@Override
	public void save(Customer customer) {
		if (customer.getId() != null && customer.getId() > 0) {
			em.merge(customer);
		} else {
			em.persist(customer);
		}
	}

	@Override
	public void delete(Long id) {
		em.remove(findOne(id));
	}

}
