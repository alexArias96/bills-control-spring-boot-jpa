package com.bolsadeideas.springboot.app.models.dao;

import com.bolsadeideas.springboot.app.models.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IProductDAO extends CrudRepository<Product, Long>{

    //First form to execute query
    @Query("select p from Product p where p.name like %?1%")
    public List<Product> findByName(String term);

    //Second form
    public List<Product> findByNameLikeIgnoreCase(String term);

}
