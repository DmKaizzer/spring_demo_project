package com.example.demo.services;

import com.example.demo.dao.Master;
import com.example.demo.dao.Studio;
import com.example.demo.dao.StudioId;
import com.example.demo.dao.User;
import com.example.demo.dto.MasterDTO;
import com.example.demo.dto.StudioDTO;
import com.example.demo.repository.MasterRepository;
import com.example.demo.repository.StudioRepository;
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
public class StudioServiceTest extends Assert {
    @InjectMocks
    StudioService studioService;
    @Mock
    StudioRepository studioRepository;
    @Mock
    MasterRepository masterRepository;

    public Studio studio;
    public User user;
    public Master master;

    @Test
    public void addStudio() {
        StudioId studioId = new StudioId("2_test", "2_address_test");
        Studio studioTest = studioService.addStudio(studioId);
        assertNotNull(studioId);
        assertEquals(studioTest.getStudioId(), studioId);
    }

    @Test
    public void addMasterTest() {
        Optional<Studio> studioEx = Optional.of(studio);
        Optional<Master> masterEx = Optional.of(master);
        Mockito.when(studioRepository.findById(studio.getStudioId())).thenReturn(studioEx);
        Mockito.when(masterRepository.findById(master.getId())).thenReturn(masterEx);
        StudioDTO studioDTO = studioService.addMaster(studio.getStudioId(), master.getId());
        assertNotNull(studioDTO);
        assertNotNull(studioDTO.getMaster());
        assertTrue(studioDTO.getMaster().contains(MasterDTO.parseMaster(master)));
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
    public void createStudio() {
        studio = new Studio();
        studio.setStudioId(new StudioId("TestTitle", "TestAddress"));
        studio.setMasters(null);
    }
}
