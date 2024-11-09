package com.abhinash.implementJWT.springBootJWTAuthentication.service;

import com.abhinash.implementJWT.springBootJWTAuthentication.dto.req.UserRequestDto;
import com.abhinash.implementJWT.springBootJWTAuthentication.dto.resp.UserResponseDto;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<UserResponseDto> getAllUser();
    public UserResponseDto createUser(UserRequestDto userRequestDto);

}
