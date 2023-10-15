package com.example.demo.services;

import com.example.demo.dto.AccessToken;
import com.example.demo.dto.Demo1;
import com.example.demo.dto.DemoCredentials;
import com.example.demo.repository.DemoRedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Demo1Service {
    private final Demo1 demo1;
    private final DemoRedisRepository redisRepository;

    private String generateAuthToken() {
        return demo1.generateAuthToken(new DemoCredentials("admin", "100"));
    }

    public String getMessageFromDemo1Project() {
        AccessToken accessToken = redisRepository.findById("Demo_project").orElse(null);
        if(accessToken == null) {
            accessToken = new AccessToken("Demo_project", generateAuthToken());
            redisRepository.save(accessToken);
        }
        return demo1.getMessage("Bearer " + accessToken.getAccessToken());
    }
}
