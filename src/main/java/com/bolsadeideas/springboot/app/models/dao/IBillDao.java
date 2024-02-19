package com.bolsadeideas.springboot.app.models.dao;

import com.bolsadeideas.springboot.app.models.entity.Bill;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IBillDao extends CrudRepository<Bill, Long> {

    //Inicia refactorización de consultas para aplicar JOIN FETCH y optimizar la ejecución
    @Query("select b from Bill b " +
            "join fetch b.customer c " +
            "join fetch b.items line " +
            "join fetch line.product " +
            "where b.id =?1") //nombre del alias punto atributo de la relación
    public Bill fetchByIdWithCustomerWithItemBillWithProduct(Long id);
}
