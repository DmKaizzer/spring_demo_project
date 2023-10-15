package com.example.demo.services;

import com.example.demo.dao.Authority;
import com.example.demo.dao.User;
import com.example.demo.dto.AuthorityDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.repository.AuthorityRepository;
import com.example.demo.repository.JpaUserRepository;
import com.example.demo.repository.RedisUserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminService {

    private final JpaUserRepository jpaUserRepository;
    private final AuthorityRepository authorityRepository;
    private final RedisUserRepository redisUserRepository;
    private final PasswordEncoder encoder;

    public List<UserDTO> getAllUsers() {
        return jpaUserRepository.findAll().stream().map(UserDTO::parseUser).toList();
    }

    public UserDTO getUserById(Integer id) {
        try {
            UserDTO cachedUser = redisUserRepository.findById(id).orElse(null);
            if (cachedUser != null)
                return cachedUser;
            Thread.sleep(5 * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return UserDTO.parseUser(jpaUserRepository.findById(id).orElse(null));
    }

    public User createUser(UserDTO userDTO) {
        User user = UserDTO.parseUserDto(userDTO);
        user.setPassword(encoder.encode(user.getPassword()));
        jpaUserRepository.save(user);
        redisUserRepository.save(UserDTO.parseUser(user));
        return user;
    }

    public User updateUser(UserDTO userDTO) {
        User user = jpaUserRepository.findById(userDTO.getId()).orElse(null);
        if (user != null) {
            user = UserDTO.parseUserDto(userDTO);
            jpaUserRepository.save(user);
        }
        return user;
    }

    public UserDTO softDeleteUser(Integer id) {
        User user = jpaUserRepository.findById(id).orElse(null);
        if (user != null) {
            user.setIsDeleted(user.getIsDeleted() == null || !user.getIsDeleted());
            jpaUserRepository.save(user);
        }
        return UserDTO.parseUser(user);
    }

    public void deleteUser(Integer id) {
        jpaUserRepository.findById(id).ifPresent(user -> jpaUserRepository.deleteById(id));
    }

    public AuthorityDTO addAuthority(AuthorityDTO authorityDTO) {
        User user = jpaUserRepository.findUserByUsername(authorityDTO.getUsername());
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
