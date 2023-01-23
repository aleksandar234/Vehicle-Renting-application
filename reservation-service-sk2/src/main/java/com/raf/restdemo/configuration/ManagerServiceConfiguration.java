package com.raf.restdemo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.io.IOException;
import java.util.Collections;

@Configuration
public class ManagerServiceConfiguration {

    @Bean
    public RestTemplate managerServiceRestTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory("http://localhost:8080/api/"));
        return restTemplate;
    }


}
