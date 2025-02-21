package com.code.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                )
                .csrf(csrf -> csrf.disable())  // Disable CSRF
                .formLogin(login -> login.disable())  // Disable Login Page
                .logout(logout -> logout.disable())  // Disable Logout
                .httpBasic(basic -> basic.disable());  // Disable Basic Auth

        return http.build();
    }
}
