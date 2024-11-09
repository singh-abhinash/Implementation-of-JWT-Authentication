package com.abhinash.implementJWT.springBootJWTAuthentication.config;

import com.abhinash.implementJWT.springBootJWTAuthentication.exp.AuthError;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
public class AuthConfig {
//        @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails user1 = User.builder().username("abhinash").password(passwordEncoder().encode("14640548")).build();
//        UserDetails user2 = User.builder().username("singh").password(passwordEncoder().encode("14640548")).build();
//        return new InMemoryUserDetailsManager(user1,user2);
//    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }
    @Bean
    public AuthError authError() {
        return new AuthError();
    }

}
