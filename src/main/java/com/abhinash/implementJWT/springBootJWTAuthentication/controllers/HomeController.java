package com.abhinash.implementJWT.springBootJWTAuthentication.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Abhinash Singh - 2024
 */

@RestController
public class HomeController {
    @GetMapping("/home")
    public String get(){
        return "Ram Ram";
    }
}
