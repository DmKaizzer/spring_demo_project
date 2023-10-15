package com.example.demo.controllers;

import com.example.demo.dao.User;
import com.example.demo.dto.AuthorityDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService service;

    @GetMapping(value = "/get-all-users")
    public List<UserDTO> getAllUsers() {
        return service.getAllUsers();
    }

    @GetMapping(value = "/get-user/{id}")
    public UserDTO getUserById(@PathVariable(value = "id") Integer id) {
        return service.getUserById(id);

    }

    @PostMapping(value = "/create-user")
    public User createUser(@RequestBody UserDTO userDTO) {
        return service.createUser(userDTO);
    }

    @PutMapping(value = "/update-user")
    public User updateUser(@RequestBody UserDTO user) {
        return service.updateUser(user);
    }

    @PostMapping("/soft-delete-user/{id}")
    public UserDTO softDeleteUser(@PathVariable("id") Integer id) {
        return service.softDeleteUser(id);
    }

    @DeleteMapping(value = "/delete-user/{id}")
    public void deleteUser(@PathVariable("id") Integer id) {
        service.deleteUser(id);
    }

    @PostMapping(value = "/add_authority")
    public AuthorityDTO addAuthority(@RequestBody AuthorityDTO authorityDTO) {
        return service.addAuthority(authorityDTO);
    }

    @GetMapping(value = "/get_authority")
    public List<AuthorityDTO> getAuthority() {
        return service.getAuthority();
    }
}
