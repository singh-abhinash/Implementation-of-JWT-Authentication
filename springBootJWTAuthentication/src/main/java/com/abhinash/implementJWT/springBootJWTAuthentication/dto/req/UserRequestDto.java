package com.abhinash.implementJWT.springBootJWTAuthentication.dto.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {

        private String name;
        private String email;
        private String password;
        private String aboutMe;
        
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getAboutMe() {
			return aboutMe;
		}
		public void setAboutMe(String aboutMe) {
			this.aboutMe = aboutMe;
		}


}
