package com.abhinash.implementJWT.springBootJWTAuthentication.repo;

import com.abhinash.implementJWT.springBootJWTAuthentication.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Abhinash Singh - 2024
 */

public interface UserRepo extends JpaRepository<UserEntity,Long> {

    public Optional<UserEntity> findByEmail(String email);

}
