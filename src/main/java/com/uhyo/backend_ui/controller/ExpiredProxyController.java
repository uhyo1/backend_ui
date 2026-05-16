package com.uhyo.backend_ui.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ExpiredProxyController {

    private final RestTemplate restTemplate = new RestTemplate();

private static final String DATA_URL = "http://localhost:9001/expired/all";

    @GetMapping("/expired/all")
    public Object getAllExpired() {
        return restTemplate.getForObject(DATA_URL, Object.class);
    }
}
