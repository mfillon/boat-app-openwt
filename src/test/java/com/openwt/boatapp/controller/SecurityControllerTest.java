package com.openwt.boatapp.controller;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(SecurityController.class)
public class SecurityControllerTest {
    @Autowired
    private MockMvc mvc;


    @Test
    public void shouldReturnAuthenticateUserDetails() throws Exception {
        mvc.perform(get("/api/user")
            .with(httpBasic("user1", "password"))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.username", is("user1")))
            .andExpect(jsonPath("$.authorities", hasSize(1)))
            .andExpect(jsonPath("$.authorities[0].authority", is("ROLE_USER")));
    }

    @Test
    public void noAuthShouldReturn401WithoutHeader() throws Exception {
        mvc.perform(get("/api/user")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isUnauthorized())
            .andExpect(header().doesNotExist("WWW-Authenticate"));
    }

    @Test
    public void wrongCredentialsShouldReturn401WithoutHeader() throws Exception {
        mvc.perform(get("/api/user")
            .with(httpBasic("user1", "wrongPassword"))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isUnauthorized())
            .andExpect(header().doesNotExist("WWW-Authenticate"));
    }

}
