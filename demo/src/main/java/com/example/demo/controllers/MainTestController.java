package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainTestController {

    @GetMapping(value = "test")
    public String getTestData() {
        return "test data";
    }
}
