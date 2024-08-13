package com.COWORK.COWORKING;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CoworkingApplicationTests {




		@BeforeEach
		public void setUp() {
			// Create an Authentication object
			Authentication auth = new UsernamePasswordAuthenticationToken(
					"user",
					"password",
					Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
			);

			// Set the Authentication object in the SecurityContext
			SecurityContextHolder.getContext().setAuthentication(auth);
		}

		@Test
		public void testUserEndpoint() {
			// Your test logic here
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			assertThat(authentication).isNotNull();
			assertThat(authentication.getName()).isEqualTo("user");
		}

		@Test
		public void testAdminEndpoint() {
			// Change the authentication to an admin user
			Authentication auth = new UsernamePasswordAuthenticationToken(
					"admin",
					"password",
					Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"))
			);
			SecurityContextHolder.getContext().setAuthentication(auth);

			// Your test logic here
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			assertThat(authentication).isNotNull();
			assertThat(authentication.getName()).isEqualTo("admin");
		}
	}



