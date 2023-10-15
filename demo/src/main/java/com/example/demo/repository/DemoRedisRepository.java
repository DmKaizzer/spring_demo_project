package com.example.demo.repository;

import com.example.demo.dto.AccessToken;
import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemoRedisRepository extends KeyValueRepository<AccessToken, String> {
}
