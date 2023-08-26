package com.hsbc.security.controller;

import com.hsbc.security.aop.auth.AuthConstant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@WebAppConfiguration
public class UserControllerTest {
    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvcUser;

    @BeforeEach
    public void prepare() {
        mockMvcUser = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void login() throws Exception {
        mockMvcUser.perform(MockMvcRequestBuilders.post("/hsbc/v1/user").
                contentType(MediaType.APPLICATION_JSON).
                content("{\"username\": \"kd\", \"password\": \"123\"}")).andExpect(MockMvcResultMatchers.status().isOk());
        MvcResult result = mockMvcUser.perform(MockMvcRequestBuilders.post("/hsbc/v1/session").
                        contentType(MediaType.APPLICATION_JSON).
                        content("{\"username\": \"kd\", \"password\": \"123\"}")).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.header().exists(AuthConstant.AUTHORIZATION_HEADER)).andReturn();
        String token = result.getResponse().getHeader(AuthConstant.AUTHORIZATION_HEADER);
        mockMvcUser.perform(MockMvcRequestBuilders.get("/hsbc/v1/user/kd/roles").header(AuthConstant.AUTHORIZATION_HEADER, token)).andExpect(MockMvcResultMatchers.status().isOk());
    }
}
