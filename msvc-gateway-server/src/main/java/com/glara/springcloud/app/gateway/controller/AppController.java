package com.glara.springcloud.app.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AppController {
    @GetMapping("/authorized")
    public Mono<Map<String, String>> authorizedMap(@RequestParam String code) {
        Map<String, String> map = new HashMap<>();
        map.put("code", code);
        return Mono.just(map);
    }

    @PostMapping("/Logout")
    public Mono<Map<String, String>> logout() {
        Map<String, String> map = new HashMap<>();
        map.put("message", "Logout successful");
        return Mono.just(map);
    }
}
