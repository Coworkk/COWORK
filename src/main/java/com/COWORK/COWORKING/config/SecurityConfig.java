package com.COWORK.COWORKING.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.http.HttpMethod.*;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    public static final String ADMIN = "admin";
    public static final String USER = "user";
    private final JwtConverter jwtConverter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests((authz) ->
                 authz.requestMatchers(GET, "/users/**").permitAll().requestMatchers(POST, "/users/login").permitAll().requestMatchers(PUT, "/users/**").permitAll()
                .requestMatchers(GET, "/admin/**").hasRole(ADMIN).requestMatchers(POST, "/admin/**").hasRole(ADMIN).requestMatchers(PUT, "/admin/**").hasRole(ADMIN)
                .requestMatchers(GET, "/user/**").hasRole(USER).requestMatchers(POST, "/user/**").hasRole(USER).requestMatchers(USER, "/user/**").hasRole(USER)
                .requestMatchers(POST,"/app/**").hasAnyRole(USER,ADMIN).anyRequest().authenticated());
        http.sessionManagement(sess -> sess.sessionCreationPolicy(
                SessionCreationPolicy.STATELESS));
        http.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtConverter)));
        return http.build();
    }

}
