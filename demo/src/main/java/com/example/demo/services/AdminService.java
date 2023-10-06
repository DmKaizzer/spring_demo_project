package com.example.demo.services;

import com.example.demo.dao.Authority;
import com.example.demo.dao.User;
import com.example.demo.dto.AuthorityDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.repository.AuthorityRepository;
import com.example.demo.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder encoder;

    @Resource(name = "redisTemplate")
    private HashOperations<String, Integer, Object> hashOperations;
    private Jedis jedis = new Jedis();

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(UserDTO::parseUser).toList();
    }

    public UserDTO getUserById(Integer id) {
        try {
            //TEST Redis
//            jedis.set("test", "testvalue");
            String cachedUser = (String) hashOperations.get("User", id.toString());
            UserDTO userDTO = cachedUser != null ? new ObjectMapper().readValue(cachedUser, UserDTO.class) : null;
            if (userDTO != null)
                return userDTO;
            Thread.sleep(5 * 1000) ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return UserDTO.parseUser(userRepository.findById(id).orElse(null));
    }

    public User createUser(UserDTO userDTO) {
        User user = UserDTO.parseUserDto(userDTO);
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        hashOperations.put("User", user.getId(), user.toString());
        return user;
    }

    public User updateUser(UserDTO userDTO) {
        User user = userRepository.findById(userDTO.getId()).orElse(null);
        if (user != null) {
            user = UserDTO.parseUserDto(userDTO);
            userRepository.save(user);
        }
        return user;
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
