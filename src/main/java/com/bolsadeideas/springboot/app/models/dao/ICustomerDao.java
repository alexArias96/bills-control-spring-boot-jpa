package com.bolsadeideas.springboot.app.models.dao;

import com.bolsadeideas.springboot.app.models.entity.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ICustomerDao extends PagingAndSortingRepository<Customer, Long> {
    @Query("select c from Customer c left join fetch c.bills b where c.id=?1")
    public Customer fetchByIdWithBills(Long id);
}
