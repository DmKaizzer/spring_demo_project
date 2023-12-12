package com.example.demo.services;

import com.example.demo.dao.Authority;
import com.example.demo.dao.User;
import com.example.demo.dto.AuthorityDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.repository.AuthorityRepository;
import com.example.demo.repository.JpaUserRepository;
import com.example.demo.repository.RedisUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {

    @InjectMocks
    public AdminService adminService;

    @Mock
    JpaUserRepository jpaUserRepository;
    @Mock
    RedisUserRepository redisUserRepository;
    @Mock
    AuthorityRepository authorityRepository;
    @Mock
    PasswordEncoder encoder;

    public User user;
    public Authority authority;

    @BeforeEach
    public void init() {
        user = createUser();
    }

    @Test
    public void createUserTest() {
        UserDTO userDTO = UserDTO.parseUser(user);
        Mockito.when(encoder.encode(userDTO.getPassword())).thenReturn(user.getPassword());
        User testUser = adminService.createUser(userDTO);
        assertEquals(testUser, user);
    }

    @Test
    public void updateUserTest() {
        Optional<User> userOptional = Optional.of(createUser());
        UserDTO userDTO = UserDTO.parseUser(user);
        userDTO.setUsername("Test_2");
        Mockito.when(jpaUserRepository.findById(userDTO.getId())).thenReturn(userOptional);
        User testUser = adminService.updateUser(userDTO);
        user.setUsername("Test_2");
        assertEquals(user, testUser);
    }

    @Test
    public void softDeleteUser() {
        Optional<User> userOptional = Optional.of(createUser());
        UserDTO userDTO = UserDTO.parseUser(user);
        Mockito.when(jpaUserRepository.findById(userDTO.getId())).thenReturn(userOptional);
        UserDTO testUser = adminService.softDeleteUser(userDTO.getId());
        user.setIsDeleted(true);
        assertEquals(UserDTO.parseUser(user), testUser);
    }

    @Test
    public void addAuthority() {
        Mockito.when(jpaUserRepository.findUserByUsername(authority.getUsername().getUsername())).thenReturn(user);
        AuthorityDTO newAuthority = new AuthorityDTO();
        newAuthority.setAuthority("ADMIN");
        newAuthority.setUsername("Test");
        AuthorityDTO expectedAuthority = adminService.addAuthority(newAuthority);
        assertEquals(AuthorityDTO.parseAuthorityDTO(authority), expectedAuthority);
    }

    public User createUser() {
        User user = new User();
        user.setId(1);
        user.setUsername("Test");
        user.setEnabled(true);
        user.setEmail("gmail.com");
        user.setPassword("testpsw");
        user.setLastActivity("2023-12-12");
        user.setPriority(5);
        user.setIsDeleted(false);
        return user;
    }

    @BeforeEach
    public void createAuthority() {
        authority = new Authority();
        authority.setAuthority("ADMIN");
        authority.setId(1);
        authority.setUsername(user);
    }
}
