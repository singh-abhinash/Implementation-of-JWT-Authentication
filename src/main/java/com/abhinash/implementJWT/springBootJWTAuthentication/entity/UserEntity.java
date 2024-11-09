package com.abhinash.implementJWT.springBootJWTAuthentication.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table (name = "userdb")
public class UserEntity implements UserDetails {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long id;
        private String name;

        @Column(unique = true, nullable = false)
        private String email;
        private String password;
        private String aboutMe;

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
        }

        @Override
        public String getUsername() {
                return this.email;
        }

        @Override
        public boolean isAccountNonExpired() {
                return true;
        }

        @Override
        public boolean isAccountNonLocked() {
                return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
                return true;
        }

        @Override
        public boolean isEnabled() {
                return true;
        }

        public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
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

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
}
