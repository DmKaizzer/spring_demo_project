package com.example.demo.controllers;

import com.example.demo.services.Demo1Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo1")
@RequiredArgsConstructor
public class Demo1Controller {
    private final Demo1Service service;

    @GetMapping(value = "/get-message")
    public ResponseEntity<String> getMessage() {
        try {
            String result = service.getMessageFromDemo1Project();
            return new ResponseEntity<>(result, HttpStatusCode.valueOf(200));
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatusCode.valueOf(500));
        }
    }
}
