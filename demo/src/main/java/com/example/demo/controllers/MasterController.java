package com.example.demo.controllers;

import com.example.demo.dao.Client;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.MasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/master")
public class MasterController {

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    MasterRepository masterRepository;

    @GetMapping(value = "/{login}/get-clients")
    private List<Client> getClients(@PathVariable("login") String login) {
        return clientRepository.getClientsByMaster(masterRepository.findById(login).get());
    }
}
