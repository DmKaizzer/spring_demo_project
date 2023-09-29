package com.example.demo.controllers;

import com.example.demo.dto.ClientDTO;
import com.example.demo.dto.MasterDTO;
import com.example.demo.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService service;

    @GetMapping(value = "/get-all-masters")
    public List<MasterDTO> getTestData() {
        return service.getAllMaster();
    }

    @PutMapping(value = "/{client_id}/{master_id}/add-master")
    public ClientDTO addMaster(@PathVariable("client_id") Long clientId, @PathVariable("master_id") Long masterId) {
        return service.addMaster(clientId, masterId);
    }

    @PutMapping(value = "/{client_id}/remove-master")
    public ClientDTO removeMaster(@PathVariable("client_id") Long clientId) {
        return service.removeMaster(clientId);
    }
}
