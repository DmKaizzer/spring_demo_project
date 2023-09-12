package com.example.demo.controllers;

import com.example.demo.dao.Studio;
import com.example.demo.services.StudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/studio")
public class StudioController {

    @Autowired
    StudioService service;

    @GetMapping(value = "/get-all")
    public List<Studio> getTestData() {
        return service.getAll();
    }
}
