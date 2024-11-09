package com.abhinash.implementJWT.springBootJWTAuthentication.controllers;

import com.abhinash.implementJWT.springBootJWTAuthentication.Auth.JwtHelper;
import com.abhinash.implementJWT.springBootJWTAuthentication.config.AuthConfig;
import com.abhinash.implementJWT.springBootJWTAuthentication.dto.req.JwtRequest;
import com.abhinash.implementJWT.springBootJWTAuthentication.dto.req.UserRequestDto;
import com.abhinash.implementJWT.springBootJWTAuthentication.dto.resp.JwtResponse;
import com.abhinash.implementJWT.springBootJWTAuthentication.dto.resp.UserResponseDto;
import com.abhinash.implementJWT.springBootJWTAuthentication.exp.UserAlreadyExistsException;
import com.abhinash.implementJWT.springBootJWTAuthentication.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import lombok.Builder;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthConfig authConfig;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private JwtHelper helper;


    @Autowired
    private UserService userService;


    @PostMapping("/create")
    public ResponseEntity<JwtResponse> createUser(@RequestBody UserRequestDto userRequestDto) {
        try {
            UserResponseDto userResponseDto = userService.createUser(userRequestDto);
            UserDetails userDetails = userDetailsService.loadUserByUsername(userResponseDto.getEmail());

            String token = this.helper.generateToken(userDetails);
            JwtResponse jwtResponse = new JwtResponse(token);  // Return token response
            return new ResponseEntity<>(jwtResponse, HttpStatus.CREATED);
        } catch (UserAlreadyExistsException ex) {
            // Handle the exception and return an appropriate response with message
            JwtResponse jwtResponse = new JwtResponse("User already exists: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(jwtResponse);
        }
    }



    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest jwtRequest) {
        this.doAuthenticate(jwtRequest.getEmail(), jwtRequest.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getEmail());
        String token = this.helper.generateToken(userDetails);
        JwtResponse jwtResponse = new JwtResponse(token);  // Return token response
        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) {
        System.out.println("Login Info");
        System.out.println(email);
        System.out.println(password);
        System.out.println("------");
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        System.out.println(authentication.toString()+"  do authenticarte");
        try {
            System.out.println("Started");
            manager.authenticate(authentication);
            System.out.println("Eneded");
            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println("Authentication successful for user: " + email);


        } catch (BadCredentialsException e) {
            System.out.println("Authentication not-successful for user: " + email);
            throw new BadCredentialsException(" Invalid Username or Password  !!");

        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler(BadCredentialsException ex) {
        return "Credentials Invalid !!";
    }



	public AuthConfig getAuthConfig() {
		return authConfig;
	}



	public void setAuthConfig(AuthConfig authConfig) {
		this.authConfig = authConfig;
	}



	public static Logger getLogger() {
		return logger;
	}
}
