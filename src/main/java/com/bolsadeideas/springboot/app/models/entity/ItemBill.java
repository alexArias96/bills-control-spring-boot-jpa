package com.bolsadeideas.springboot.app.models.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "items_bills")
public class ItemBill implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double calculateAmount(){
        return amount.doubleValue() * product.getPrice();
    }
    private static final long serialVersionUID = 1L;
}
