package com.uhyo.backend_ui.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // ← このクラスが REST API を提供するコントローラであることを示す
public class HelloController {

    /**
     * /hello にアクセスされた時に実行されるメソッド
     * ここでは単純に文字列を返すだけのテスト用 API
     */
    @GetMapping("/hello")
    public String hello() {
        return "Hello from backend_ui!";
    }
}
