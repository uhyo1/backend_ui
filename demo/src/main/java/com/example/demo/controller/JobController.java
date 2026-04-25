package com.example.demo.controller;

import com.example.demo.dto.JobRequest;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class JobController {

    @PostMapping("/job")
    public Map<String, Object> runJob(@RequestBody JobRequest request) {

        // 本来は Python → job_id → jobCenter → systemwalker など
        // 今回は張りぼてで受け取った内容をそのまま返す

        Map<String, Object> result = new HashMap<>();
        result.put("status", "OK");
        result.put("received_customers", request.getCustomers());
        result.put("job_rslt_json", "{ \"dummy\": true }");

        return result;
    }
}
