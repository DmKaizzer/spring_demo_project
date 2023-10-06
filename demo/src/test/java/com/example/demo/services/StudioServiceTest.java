package com.example.demo.services;

import com.example.demo.dao.Master;
import com.example.demo.dao.Studio;
import com.example.demo.dao.StudioId;
import com.example.demo.dao.User;
import com.example.demo.dto.StudioDTO;
import com.example.demo.repository.MasterRepository;
import com.example.demo.repository.StudioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class StudioServiceTest {
    @InjectMocks
    StudioService studioService;
    @Mock
    StudioRepository studioRepository;
    @Mock
    MasterRepository masterRepository;

    public Studio studio;
    public User user;
    public Master master;

    @BeforeEach
    public void init() {
        studio = createStudio();
        user = createUser();
        master = createMaster();
    }

    @Test
    public void addStudio() {
        StudioId studioId = new StudioId("TestTitle", "TestAddress");
        Studio studioTest = studioService.addStudio(studioId);
        assertEquals(studio, studioTest);
    }

    @Test
    public void addMasterTest() {
        Optional<Studio> studioEx = Optional.of(createStudio());
        Optional<Master> masterEx = Optional.of(createMaster());
        Mockito.when(studioRepository.findById(studio.getStudioId())).thenReturn(studioEx);
        Mockito.when(masterRepository.findById(master.getId())).thenReturn(masterEx);
        StudioDTO studioDTO = studioService.addMaster(studio.getStudioId(), master.getId());
        Studio expectedStudio = createStudio();
        expectedStudio.setMasters(List.of(createMaster()));
        assertEquals(StudioDTO.parseStudio(expectedStudio), studioDTO);
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

    public Studio createStudio() {
        studio = new Studio();
        studio.setStudioId(new StudioId("TestTitle", "TestAddress"));
        studio.setMasters(null);
        return studio;
    }
}
