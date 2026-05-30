package com.uhyo.backend_ui.controller;

import com.uhyo.common.dto.ui.JobStartRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/ui/job")
public class JobUiController {

    private final RestTemplate rest = new RestTemplate();

    @PostMapping("/start")
    public ResponseEntity<?> start(@RequestBody JobStartRequest req) {

        // backend_data_management へ転送
        String result = rest.postForObject(
            "http://localhost:9001/job/start",
            req,
            String.class
        );

        return ResponseEntity.ok(result);
    }
}
