package com.bolsadeideas.springboot.app.models.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "bills")
public class Bill implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String observation;
    @Temporal(TemporalType.DATE)
    @Column(name = "create_at")
    private Date createAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "bill_id")
    private List<ItemBill> items;

    public Bill() {
        this.items = new ArrayList<ItemBill>();
    }

    @PrePersist
    public void prePersist(){
        createAt = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemBill> getItems() {
        return items;
    }

    public void setItems(List<ItemBill> items) {
        this.items = items;
    }

    public void addItemBill(ItemBill item){
        this.items.add(item);
    }

    public Double getTotal(){
        Double total = 0.0;
        int size = items.size();
        
        //Atravez de un for vamos a recorrer los elementos y calculamos el total
        for (int i = 0; i < size; i++) {
            total += items.get(i).calculateAmount();
        }
        return total;
    }
    private static final long serialVersionUID = 1L;
}
