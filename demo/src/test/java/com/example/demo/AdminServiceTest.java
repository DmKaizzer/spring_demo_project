package com.example.demo;

import com.example.demo.dao.User;
import com.example.demo.services.AdminService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AdminServiceTest extends Assert {
    @Mock
    public AdminService adminService;

    @Test
    public void getAllUserTest() {
        User user = new User();
        user.setId(1);
        user.setUsername("Test");
        user.setEnabled(true);
        user.setEmail("gmail.com");
        user.setPassword("test_psw");
        user.setLastActivity("2023-12-12");
        user.setPriority(5);
        user.setIsDeleted(false);
        user = adminService.createUser(user);
        assertNotNull(user);
    }

}
