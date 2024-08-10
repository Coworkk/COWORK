package com.COWORK.COWORKING.config;
import jakarta.ws.rs.HttpMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {
    @Autowired
    private JwtAuthConverter jwtAuthConverter;
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web) ->{
            web.ignoring().requestMatchers(HttpMethod.POST, "/public/**", "/users/**");
            web.ignoring().requestMatchers(HttpMethod.GET, "/public/**");
            web.ignoring().requestMatchers(HttpMethod.PUT, "/public/**","/users/**");
            web.ignoring().requestMatchers(HttpMethod.OPTIONS, "/**").requestMatchers(
                    "/v3/api-docs/**","/configuration/**","/swagger-ui/**", "/swagger-resources/**", "/webjars/**","/api-docs/**");
        };
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

                  return httpSecurity
                          .authorizeHttpRequests(
                                  auth->auth
                                          .anyRequest()
                                          .authenticated()
                          )
                          .oauth2ResourceServer( oauth2->oauth2
                                          .jwt(jwt -> jwt
                                                  .jwtAuthenticationConverter(jwtAuthConverter)
                                          )
                                  )
                                  .sessionManagement(auth->auth.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                  .build();
    }
}
