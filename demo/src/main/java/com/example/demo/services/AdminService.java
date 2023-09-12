package com.example.demo.services;

import com.example.demo.dao.Authority;
import com.example.demo.dao.User;
import com.example.demo.dto.AuthorityDTO;
import com.example.demo.repository.AuthorityRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthorityRepository authorityRepository;
    @Autowired
    PasswordEncoder encoder;

    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.size() != 0 ? users : new ArrayList<>();
    }

    public User createUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user = userRepository.save(user);
        return user;
    }

    public User updateUser(User user) {
        Optional<User> findingUser = userRepository.findById(user.getId());
        if (findingUser.isPresent()) {
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

    public User softDeleteUser(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User deletingUser = user.get();
            deletingUser.setIsDeleted(deletingUser.getIsDeleted() == null || !deletingUser.getIsDeleted());
            deletingUser = userRepository.save(deletingUser);
            return deletingUser;
        }
        return null;
    }

    public void deleteUser(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
        }
    }

    public Authority addAuthority(AuthorityDTO authorityDTO) {
        User user = userRepository.findUserByUsername(authorityDTO.getUsername());
        Authority authority = new Authority();
        authority.setAuthority(authorityDTO.getAuthority());
        authority.setUsername(user);
        authority = authorityRepository.save(authority);
        return authority;
    }

    public List<Authority> getAuthority() {
        List<Authority> authorities = authorityRepository.findAll();
        return authorities.size() != 0 ? authorities : new ArrayList<>();
    }
}
