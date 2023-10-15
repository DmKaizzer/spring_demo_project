package com.example.demo.repository;

import com.example.demo.dao.User;
import com.example.demo.dto.UserDTO;
import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.stereotype.Repository;

@Repository("redisUserRepository")
public interface RedisUserRepository extends KeyValueRepository<UserDTO, Integer> {
}
