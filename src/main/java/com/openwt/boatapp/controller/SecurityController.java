package com.openwt.boatapp.controller;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    @GetMapping(value = "/api/user")
    public UserDetails currentUserName(Authentication authentication) {
        return (UserDetails) authentication.getPrincipal();
    }
}
