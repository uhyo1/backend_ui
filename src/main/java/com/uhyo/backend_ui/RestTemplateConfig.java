package com.uhyo.backend_ui.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplate を Spring に登録するための設定クラス
 * 他のクラスから @Autowired で利用できるようになる
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        // HTTP 通信を行うための RestTemplate インスタンスを返す
        return new RestTemplate();
    }
}
