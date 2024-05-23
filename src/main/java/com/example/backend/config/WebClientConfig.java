package com.example.backend.config;

import com.example.backend.weather.constant.ApiConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public static WebClient.Builder webClient() {
        return WebClient.builder();
    }

}
