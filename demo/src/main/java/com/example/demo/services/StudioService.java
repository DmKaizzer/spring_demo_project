package com.example.demo.services;

import com.example.demo.dao.Studio;
import com.example.demo.repository.StudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudioService {
    @Autowired
    StudioRepository studioRepository;
    public List<Studio> getAll() {
        return studioRepository.findAll();
    }
}
