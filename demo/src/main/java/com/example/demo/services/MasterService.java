package com.example.demo.services;

import com.example.demo.dao.Master;
import com.example.demo.repository.MasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MasterService {
    @Autowired
    MasterRepository masterRepository;

    public List<Master> getAllMasters() {
        return masterRepository.findAll();
    }
}
