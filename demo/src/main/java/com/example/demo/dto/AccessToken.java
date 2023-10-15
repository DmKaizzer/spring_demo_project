package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash(value = "AccessToken", timeToLive = 60L)
@AllArgsConstructor
public class AccessToken {

    public String id;
    public String accessToken;

}
