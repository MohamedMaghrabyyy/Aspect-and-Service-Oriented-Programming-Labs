package com.example.lab5.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class HomeController {

    @GetMapping("/restricted")
    @PreAuthorize("isAuthenticated()")
    public String testAuthentication() {
        return "protected endpoint";
    }

    @GetMapping("/all")
    public String testPublicAccess() {
        return "unprotected endpoint.";
    }

}