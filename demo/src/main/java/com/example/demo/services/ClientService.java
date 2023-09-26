package com.example.demo.services;

import com.example.demo.dao.Client;
import com.example.demo.dao.Master;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.MasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ClientService {
    ClientRepository clientRepository;
    @Autowired
    public ClientService (ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAll() {
        return clientRepository.findAll();
    }
}
