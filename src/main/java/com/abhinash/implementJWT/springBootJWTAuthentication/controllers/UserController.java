package com.abhinash.implementJWT.springBootJWTAuthentication.controllers;

import com.abhinash.implementJWT.springBootJWTAuthentication.dto.resp.UserResponseDto;
import com.abhinash.implementJWT.springBootJWTAuthentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Abhinash Singh - 2024
 */

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUser(){
        return new ResponseEntity<>( userService.getAllUser(), HttpStatus.OK);
    }

}
