package com.example.demo.controllers;

import com.example.demo.dao.Authority;
import com.example.demo.dao.User;
import com.example.demo.dto.AuthorityDTO;
import com.example.demo.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService service;

    @GetMapping(value = "/get-all-users")
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    @PostMapping(value = "/create-user")
    public User createUser(@RequestBody User user) {
        return service.createUser(user);
    }

    @PutMapping(value = "/update-user")
    public User updateUser(@RequestBody User user) {
        return service.updateUser(user);
    }

    @PostMapping("/soft-delete-user/{id}")
    public User softDeleteUser(@PathVariable("id") Integer id) {
        return service.softDeleteUser(id);
    }

    @DeleteMapping(value = "/delete-user/{id}")
    public void deleteUser(@PathVariable("id") Integer id) {
        service.deleteUser(id);
    }

    @PostMapping(value = "/add_authority")
    public Authority addAuthority(@RequestBody AuthorityDTO authorityDTO) {
        return service.addAuthority(authorityDTO);
    }

    @GetMapping(value = "/get_authority")
    public List<Authority> getAuthority() {
        return service.getAuthority();
    }
}
