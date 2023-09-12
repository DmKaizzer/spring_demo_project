package com.example.demo.controllers;

import com.example.demo.dao.Client;
import com.example.demo.dao.Master;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.MasterRepository;
import com.example.demo.services.MasterService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/master")
public class MasterController {
/*
* разобраться с таблицами
* сервисы, тесты
* */
    @Autowired
    MasterService service;

    @GetMapping(value = "/{login}/get-clients")
    public List<Master> getClients(@PathVariable("login") String login) {
        return service.getAllMasters();
    }
}
