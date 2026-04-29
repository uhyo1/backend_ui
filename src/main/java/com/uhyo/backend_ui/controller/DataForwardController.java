package com.uhyo.backend_ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * backend_ui から backend_data_management へ
 * API を呼び出すためのコントローラ
 */
@RestController
public class DataForwardController {

    // RestTemplate を注入（HTTP 通信に使用）
    @Autowired
    private RestTemplate restTemplate;

    /**
     * GET /ui/call-data にアクセスされた時に実行されるメソッド
     * backend_data_management の /data/test を呼び出して結果を返す
     */
    @GetMapping("/ui/call-data")
    public String callDataManagement() {

        // 呼び出し先の URL（今回は固定）
        String url = "http://localhost:9001/data/test";

        // backend_data_management の API を呼び出し、結果を取得
        String response = restTemplate.getForObject(url, String.class);

        // 取得した結果をそのまま返す
        return "backend_data_management の返答: " + response;
    }
}
