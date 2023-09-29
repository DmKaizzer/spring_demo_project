package com.example.demo.services;

import com.example.demo.dao.Master;
import com.example.demo.dto.ClientDTO;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.MasterRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MasterService {

    private final MasterRepository masterRepository;
    private final ClientRepository clientRepository;

    public List<ClientDTO> getAllMasters(Long masterId) {
        Master master = masterRepository.findById(masterId).orElse(null);
        return clientRepository.findClientByMaster(master).stream().map(ClientDTO::parseClient).toList();
    }

    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll().stream().map(ClientDTO::parseClient).toList();
    }
}
