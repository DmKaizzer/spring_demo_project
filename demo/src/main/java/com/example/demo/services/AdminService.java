package com.example.demo.services;

import com.example.demo.dao.Authority;
import com.example.demo.dao.User;
import com.example.demo.dto.AuthorityDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.repository.AuthorityRepository;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder encoder;

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(UserDTO::parseUser).toList();
    }

    public User createUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user = userRepository.save(user);
        return user;
    }

    public UserDTO updateUser(UserDTO userDTO) {
        User user = userRepository.findById(userDTO.getId()).orElse(null);
        if (user != null) {
            userDTO = UserDTO.parseUser(user);
        }
        return userDTO;
    }

    public UserDTO softDeleteUser(Integer id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setIsDeleted(user.getIsDeleted() == null || !user.getIsDeleted());
            userRepository.save(user);
        }
        return UserDTO.parseUser(user);
    }

    public void deleteUser(Integer id) {
        userRepository.findById(id).ifPresent(user -> userRepository.deleteById(id));
    }

    public AuthorityDTO addAuthority(AuthorityDTO authorityDTO) {
        User user = userRepository.findUserByUsername(authorityDTO.getUsername());
        Authority authority = new Authority();
        authority.setAuthority(authorityDTO.getAuthority());
        authority.setUsername(user);
        authorityRepository.save(authority);
        return AuthorityDTO.parseAuthorityDTO(authority);
    }

    public List<AuthorityDTO> getAuthority() {
        return authorityRepository.findAll().stream().map(AuthorityDTO::parseAuthorityDTO).toList();
    }
}
