package com.hsbc.security.service;

import com.hsbc.security.api.AuthService;
import com.hsbc.security.api.UserService;
import com.hsbc.security.api.dto.CreateUserRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AuthServiceTest {
    @Autowired
    private AuthService authServive;
    @Autowired
    private UserService userService;

    @BeforeEach
    public void prepare() {
        CreateUserRequest request = new CreateUserRequest();
        request.setUsername("kd");
        request.setPassword("123");
        userService.create(request);
    }

    @Test
    public void login() {
        Assertions.assertTrue(authServive.isValid("kd", "123"));
        Assertions.assertFalse(authServive.isValid("kd", "321"));
        authServive.saveToken("kd");

    }

    @Test
    public void logout() {
        authServive.isValid("kd", "123");
    }
}
