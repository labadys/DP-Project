package com.example.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/",
                                "/login",
                                "/registration",
                                "/error",
                                "/error/**",
                                "/static/**",
                                "/favicon.ico",
                                "/favicon-*.png",
                                "/h2-console/**",

                                // Swagger UI ресурсы
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-resources/**",
                                "/swagger-resources",
                                "/webjars/**",
                                "/swagger-ui-custom/**",
                                "/swagger-config"
                        ).permitAll()

                        //  API endpoints
                        .requestMatchers(
                                "/api/users/**",
                                "/api/reviews/**"
                        ).permitAll()

                        .anyRequest().authenticated()
                )
                .headers(headers -> headers
                        .frameOptions(frameOptions -> frameOptions.disable())
                )
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(
                                "/h2-console/**",
                                "/api/reviews/**",
                                "/swagger-ui/**",
                                "/v3/api-docs/**"
                        )
                );

        return http.build();
    }
}