package com.example.demo.services;

import com.example.demo.dao.Client;
import com.example.demo.dao.Master;
import com.example.demo.dao.User;
import com.example.demo.dto.ClientDTO;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.MasterRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest extends Assert {

    @InjectMocks
    ClientService clientService;
    @Mock
    ClientRepository clientRepository;
    @Mock
    MasterRepository masterRepository;

    public Client client;
    public Master master;
    public User user;

    @Test
    public void addMasterTest() {
        Optional<Client> clientExp = Optional.of(client);
        Optional<Master> masterExp = Optional.of(master);
        Mockito.when(clientRepository.findById(client.getClientId())).thenReturn(clientExp);
        Mockito.when(masterRepository.findById(master.getId())).thenReturn(masterExp);
        ClientDTO clientDTO = clientService.addMaster(client.getClientId(), master.getId());
        assertNotNull(clientDTO);
        assertEquals(clientDTO.getMaster(), master.getName());
    }

    @Test
    public void removeMasterTest() {
        Optional<Client> clientExp = Optional.of(client);
        Mockito.when(clientRepository.findById(client.getClientId())).thenReturn(clientExp);
        ClientDTO clientDTO = clientService.removeMaster(client.getClientId());
        assertNotNull(clientDTO);
        assertNull(clientDTO.getMaster());
    }

    @Before
    public void createUser() {
        user = new User();
        user.setId(1);
        user.setUsername("Test");
        user.setEnabled(true);
        user.setEmail("gmail.com");
        user.setPassword("test_psw");
        user.setLastActivity("2023-12-12");
        user.setPriority(5);
        user.setIsDeleted(false);
    }
    @Before
    public void createMaster() {
        master = new Master();
        master.setId(1L);
        master.setLastName("TestMaster lastName");
        master.setName("TestMaster name");
        master.setLogin(user);
        master.setSkill("Skill");
        master.setStudios(null);
        master.setStyle("Style");
    }

    @Before
    public void createClient() {
        client = new Client();
        client.setClientId(1L);
        client.setAge(18);
        client.setLastName("Test lastname");
        client.setName("Test name");
        client.setUsername(user);
        client.setMaster(null);
    }
}
