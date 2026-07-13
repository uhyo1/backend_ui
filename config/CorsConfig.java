/*
  File: CorsConfig.java
  Role: backend_ui 全体の CORS 設定を行う（Spring 6.x 対応）
  Created: 2026-07-12
  Author: Seiji
*/

package com.uhyo.backend_ui.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")   // Spring 6.x では origins="*" は禁止
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(false);     // credentials=true と "*" は併用不可
    }
}
