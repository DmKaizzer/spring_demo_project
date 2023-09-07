package com.example.demo.controllers;

import com.example.demo.dao.Authority;
import com.example.demo.dao.User;
import com.example.demo.dto.AuthorityDTO;
import com.example.demo.repository.AuthorityRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthorityRepository authorityRepository;

    //TODO: таблицу с составным primary_key

    @GetMapping(value = "/get-test-data")
    public String getTestData() {
        return "test admin data";
    }
    @GetMapping(value = "/get-all-users")
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.size() != 0 ? users : new ArrayList<>();
    }

    @PostMapping(value = "/create-user")
    public User createUser(@RequestBody User user) {
        user.setPassword(new BCryptPasswordEncoder(12).encode(user.getPassword()));
        user = userRepository.save(user);
        return user;
    }

    @PutMapping(value = "/update-user")
    public User updateUser(@RequestBody User user) {
        Optional<User> findingUser = userRepository.findById(user.getId());
        if(findingUser.isPresent()) {
            User newUser = findingUser.get();
            newUser.setEmail(user.getEmail());
            newUser.setEnabled(user.getEnabled());
            newUser.setUsername(user.getUsername());
            newUser.setPassword(user.getPassword());
            newUser.setPriority(user.getPriority());
            newUser.setLastActivity(user.getLastActivity());
            newUser = userRepository.save(newUser);
            return newUser;
        }
        return null;
    }

    @PostMapping("/soft-delete-user/{id}")
    public User softDeleteUser(@PathVariable("id") Integer id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            User deletingUser = user.get();
            deletingUser.setIsDeleted(deletingUser.getIsDeleted() == null || !deletingUser.getIsDeleted());
            deletingUser = userRepository.save(deletingUser);
            return deletingUser;
        }
        return null;
    }

    @DeleteMapping(value = "/delete-user/{id}")
    public void deleteUser(@PathVariable("id") Integer id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            userRepository.deleteById(id);
        }
    }

    @PostMapping(value = "/add_authority")
    public Authority addAuthority(@RequestBody AuthorityDTO authorityDTO) {
        User user = userRepository.findUserByUsername(authorityDTO.getUsername());
        Authority authority = new Authority();
        authority.setAuthority(authorityDTO.getAuthority());
        authority.setUsername(user);
        authority = authorityRepository.save(authority);
        return authority;
    }

    @GetMapping(value = "/get_authority")
    public List<Authority> getAuthority() {
        List<Authority> authorities = authorityRepository.findAll();
        return authorities.size() != 0 ? authorities : new ArrayList<>();
    }
}
