package com.example.demo.services;

import com.example.demo.dao.Client;
import com.example.demo.dao.Master;
import com.example.demo.dao.User;
import com.example.demo.dto.ClientDTO;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.MasterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @InjectMocks
    ClientService clientService;
    @Mock
    ClientRepository clientRepository;
    @Mock
    MasterRepository masterRepository;

    public Client client;
    public Master master;
    public User user;

    @BeforeEach
    public void init() {
        client = createClient();
        master = createMaster();
        user = createUser();
    }

    @Test
    public void addMasterTest() {
        Optional<Client> clientExp = Optional.of(createClient());
        Optional<Master> masterExp = Optional.of(createMaster());
        Mockito.when(clientRepository.findById(client.getClientId())).thenReturn(clientExp);
        Mockito.when(masterRepository.findById(master.getId())).thenReturn(masterExp);
        Client expectedClient = createClient();
        expectedClient.setMaster(createMaster());
        ClientDTO clientDTO = clientService.addMaster(client.getClientId(), master.getId());
        assertEquals(ClientDTO.parseClient(expectedClient), clientDTO);
    }

    @Test
    public void removeMasterTest() {
        Optional<Client> clientExp = Optional.of(createClient());
        Mockito.when(clientRepository.findById(client.getClientId())).thenReturn(clientExp);
        client.setMaster(createMaster());
        ClientDTO clientDTO = clientService.removeMaster(client.getClientId());
        Client expectedClient = createClient();
        assertEquals(ClientDTO.parseClient(expectedClient), clientDTO);
    }

    public User createUser() {
        user = new User();
        user.setId(1);
        user.setUsername("Test");
        user.setEnabled(true);
        user.setEmail("gmail.com");
        user.setPassword("test_psw");
        user.setLastActivity("2023-12-12");
        user.setPriority(5);
        user.setIsDeleted(false);
        return user;
    }

    public Master createMaster() {
        master = new Master();
        master.setId(1L);
        master.setLastName("TestMaster lastName");
        master.setName("TestMaster name");
        master.setLogin(user);
        master.setSkill("Skill");
        master.setStudios(null);
        master.setStyle("Style");
        return master;
    }

    public Client createClient() {
        client = new Client();
        client.setClientId(1L);
        client.setAge(18);
        client.setLastName("Test lastname");
        client.setName("Test name");
        client.setUsername(user);
        client.setMaster(null);
        return client;
    }
}
