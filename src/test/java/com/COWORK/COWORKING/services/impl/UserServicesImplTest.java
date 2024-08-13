package com.COWORK.COWORKING.services.impl;

import com.COWORK.COWORKING.dto.LogInRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class UserServicesImplTest {
    @Autowired
    private UserServicesImpl userServices;

    @Test
    void addARoleToProject() {
       var response= userServices.addARoleToProject("were");
       assertThat(response).isNotNull();
    }

    @Test
    void testLogIn() throws JsonProcessingException {
        LogInRequest  logInRequest = new LogInRequest();
        logInRequest.setPassword("12345");
        logInRequest.setUsername("fatoyeayomide123456@gmail.com");
        var response = userServices.logIn(logInRequest);
        System.out.println(new ObjectMapper().writeValueAsString(response));
    }


}