package com.bolsadeideas.springboot.app.models.service;

import com.bolsadeideas.springboot.app.models.entity.Client;

import java.util.List;

public interface IClienteService {
    public List<Client> findAll();

    public void save(Client client);

    public Client findOne(Long id);

    public void delete(Long id);
}