package com.abhinash.implementJWT.springBootJWTAuthentication.service.imp;

import com.abhinash.implementJWT.springBootJWTAuthentication.config.AuthConfig;
import com.abhinash.implementJWT.springBootJWTAuthentication.dto.req.UserRequestDto;
import com.abhinash.implementJWT.springBootJWTAuthentication.dto.resp.UserResponseDto;
import com.abhinash.implementJWT.springBootJWTAuthentication.entity.UserEntity;
import com.abhinash.implementJWT.springBootJWTAuthentication.exp.UserAlreadyExistsException;
import com.abhinash.implementJWT.springBootJWTAuthentication.repo.UserRepo;
import com.abhinash.implementJWT.springBootJWTAuthentication.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Abhinash Singh - 2024
 */

@Service
public class UserImp implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AuthConfig authConfig;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepo.findByEmail(username).orElseThrow(()->new RuntimeException("User not found"));
        System.out.println("Retrived Data");
        System.out.println(user.getPassword()+"Retrived Password");
        System.out.println(user.getUsername());
        System.out.println(user.getId());
        System.out.println(user.getEmail());
        System.out.println("-----");
        return user;
    }

    @Override
    public List<UserResponseDto> getAllUser() {
        List<UserEntity> userEnitiys = userRepo.findAll();
        List<UserResponseDto> userResponseDtoList = userEnitiys.stream().map(user->this.userEntityToUserRespDto(user)).collect(Collectors.toList());
        return userResponseDtoList;


    }
    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        Optional<UserEntity> foundUser = this.userRepo.findByEmail(userRequestDto.getEmail());
        if (foundUser.isEmpty()) {
        	UserEntity user = this.userReqDtoToUserEntity(userRequestDto);
            user.setPassword(authConfig.passwordEncoder().encode(user.getPassword()));
            UserEntity createdUser = userRepo.save(user);
            return this.userEntityToUserRespDto(createdUser);
        } else {
            // User already exists, throw an exception
            throw new UserAlreadyExistsException("User with email " + userRequestDto.getEmail() + " already exists");
        }
    }


    public UserEntity userReqDtoToUserEntity(UserRequestDto userReqDto){
    	UserEntity user = this.modelMapper.map(userReqDto,UserEntity.class);
        return user;
    }
    public UserResponseDto userEntityToUserRespDto(UserEntity user){
        UserResponseDto userRespDto = this.modelMapper.map(user,UserResponseDto.class);
        return userRespDto;
    }
}
