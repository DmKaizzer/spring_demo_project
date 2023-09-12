package com.example.demo.controllers;

import com.example.demo.dao.Client;
import com.example.demo.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientService service;

    @GetMapping(value = "/get-all")
    public List<Client> getTestData() {
        return service.getAll();
    }
}
