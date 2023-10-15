package com.example.demo.dto;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "demo")
public interface Demo1 {

    @PostMapping("/auth")
    public String generateAuthToken(@RequestBody DemoCredentials credentials);

    @GetMapping("/test/get-test-message")
    public String getMessage(@RequestHeader("Authorization") String accessToken);
}
