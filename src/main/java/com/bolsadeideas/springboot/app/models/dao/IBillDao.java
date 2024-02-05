package com.bolsadeideas.springboot.app.models.dao;

import com.bolsadeideas.springboot.app.models.entity.Bill;
import org.springframework.data.repository.CrudRepository;

public interface IBillDao extends CrudRepository<Bill, Long> {

}
