package com.abhinash.implementJWT.springBootJWTAuthentication.dto.resp;

import lombok.*;
import lombok.Builder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class JwtResponse {
    private String token;
    private String message;  // Add a message field to handle errors or additional info
    
    public JwtResponse(String token) {
        this.token = token;
    }

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
