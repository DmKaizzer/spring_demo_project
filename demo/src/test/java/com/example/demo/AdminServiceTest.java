package com.example.demo;

import com.example.demo.dao.User;
import com.example.demo.repository.AuthorityRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.AdminService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    @Test
    public void getAllUserTest() {
        User expectedUser = createUser();
        Mockito.when(adminService.createUser(expectedUser)).thenReturn(expectedUser);
        assertNotNull(adminService.createUser(expectedUser));
        assertEquals(adminService.createUser(expectedUser), expectedUser);
    }

    private User createUser() {
        User user = new User();
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

}
