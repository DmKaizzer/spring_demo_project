package com.example.demo.controllers;

import com.example.demo.dto.ClientDTO;
import com.example.demo.services.MasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/master")
@RequiredArgsConstructor
public class MasterController {
    private final MasterService service;

    @GetMapping(value = "/{id}/get-clients")
    public List<ClientDTO> getClientsOfMaster(@PathVariable("id") Long id) {
        return service.getAllMasters(id);
    }

    @GetMapping(value = "/get-all-clients")
    public List<ClientDTO> getAllClients() {
        return service.getAllClients();
    }
}
