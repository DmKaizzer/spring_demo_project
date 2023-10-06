package com.example.demo.services;

import com.example.demo.dao.Client;
import com.example.demo.dao.Master;
import com.example.demo.dto.ClientDTO;
import com.example.demo.dto.MasterDTO;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.MasterRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ClientService {

    private final ClientRepository clientRepository;
    private final MasterRepository masterRepository;

    public List<MasterDTO> getAllMaster() {
        return masterRepository.findAll().stream().map(MasterDTO::parseMaster).toList();
    }

    @Cacheable(cacheNames = "masterId", key = "#masterId")
    public ClientDTO addMaster(Long clientId, Long masterId) {
        Client client = clientRepository.findById(clientId).orElse(null);
        Master master = masterRepository.findById(masterId).orElse(null);
        if(client != null && master != null) {
            client.setMaster(master);
            clientRepository.save(client);
        }
        return ClientDTO.parseClient(client);
    }

    @Cacheable(value = "client", key = "#clientId")
    public ClientDTO removeMaster(Long clientId) {
        Client client = clientRepository.findById(clientId).orElse(null);
        if (client != null) {
            client.setMaster(null);
            clientRepository.save(client);
        }
        return ClientDTO.parseClient(client);
    }
}
