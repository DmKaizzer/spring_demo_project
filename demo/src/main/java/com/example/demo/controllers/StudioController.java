package com.example.demo.controllers;

import com.example.demo.dao.Studio;
import com.example.demo.dao.StudioId;
import com.example.demo.dto.StudioDTO;
import com.example.demo.services.StudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/studio")
public class StudioController {

    @Autowired
    StudioService service;

    @GetMapping(value = "/get-all")
    public List<StudioDTO> getTestData() {
        return service.getAll();
    }

    @PostMapping(value = "/add-studio")
    public Studio addStudio(@RequestBody StudioId id) {
        return service.addStudio(id);
    }

    @PutMapping(value = "/{master_id}/add-master")
    public StudioDTO addMaster(@RequestBody StudioId id, @PathVariable("master_id") Long masterId) {
        return service.addMaster(id, masterId);
    }
}
