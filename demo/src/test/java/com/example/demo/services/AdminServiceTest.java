package com.example.demo.services;

import com.example.demo.dao.Authority;
import com.example.demo.dao.User;
import com.example.demo.dto.AuthorityDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.repository.AuthorityRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.AdminService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.beans.Encoder;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class AdminServiceTest extends Assert {
    @InjectMocks
    public AdminService adminService;

    @Mock
    UserRepository userRepository;
    @Mock
    AuthorityRepository authorityRepository;
    @Mock
    PasswordEncoder encoder;

    public User user;
    public Authority authority;

    @Test
    public void createUserTest() {
        UserDTO userDTO = UserDTO.parseUser(user);
        userDTO.setUsername("USER DTO TEST NAME");
        User testUser = adminService.createUser(userDTO);
        assertNotNull(testUser);
        assertEquals(testUser.getUsername(), userDTO.getUsername());
    }

    @Test
    public void updateUserTest() {
        Optional<User> userEx = Optional.of(user);
        Mockito.when(userRepository.findById(user.getId())).thenReturn(userEx);
        UserDTO userDTO = UserDTO.parseUser(user);
        userDTO.setUsername("Test_2");
        User expectedUser = adminService.updateUser(userDTO);
        assertNotNull(expectedUser);
        assertEquals(user.getId(), expectedUser.getId());
        assertNotEquals(user.getUsername(), expectedUser.getUsername());
    }

    @Test
    public void softDeleteUser() {
        Optional<User> userEx = Optional.of(user);
        UserDTO userDTO = UserDTO.parseUser(user);
        Mockito.when(userRepository.findById(user.getId())).thenReturn(userEx);
        //user обновляется вместе с expectedUser
        UserDTO expectedUser = adminService.softDeleteUser(userDTO.getId());
        assertNotNull(expectedUser);
        assertEquals(user.getId(), expectedUser.getId());
//        assertNotEquals(user.getIsDeleted(), expectedUser.getIsDeleted()); не работает!!!!
        assertTrue(expectedUser.getIsDeleted());
    }

    @Test
    public void addAuthority() {
        Mockito.when(userRepository.findUserByUsername(authority.getUsername().getUsername())).thenReturn(user);
        AuthorityDTO newAuthority = new AuthorityDTO();
        newAuthority.setAuthority(authority.getAuthority());
        newAuthority.setUsername(user.getUsername());
        AuthorityDTO expectedAuthority = adminService.addAuthority(newAuthority);
        assertNotNull(expectedAuthority);
        assertEquals(expectedAuthority.getUsername(), user.getUsername());
        assertEquals(expectedAuthority.getAuthority(), authority.getAuthority());
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
    public void createAuthority() {
        authority = new Authority();
        authority.setAuthority("ADMIN");
        authority.setId(1);
        authority.setUsername(user);
    }
}
