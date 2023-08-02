package com.univesp.pi.pizzariacomparator.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)

public class WebSecurityConfig {
    @Autowired
    private FilterToken filter;

    private static final String[] AUTH_WHITELIST = {
        // -- Swagger UI v2
        "/v2/api-docs", "/swagger-resources", "/swagger-resources/", "/configuration/ui",
        "/configuration/security", "/swagger-ui.html", "/webjars/"
        // -- Swagger UI v3 (OpenAPI)
        , "/v3/api-docs/**", "/swagger-ui/", "/api-docs/","/v3/api-docs.yaml", "/swagger-ui.html",
        "/v1/api/usuario/**","/swagger-ui/index.html","/api-docs/swagger-config","/api-docs/**",
        // other public endpoints of your API may be appended to this array
        "/h2-console/", "/swagger-ui/**", "/swagger-ui/v1/api/usuario/login"
    };

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
        return http
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeHttpRequests()
            .antMatchers(HttpMethod.POST, "/v1/api/usuario/**").permitAll() 
            .antMatchers(HttpMethod.POST, "/v1/api/role/**").permitAll() 
            .antMatchers(AUTH_WHITELIST).permitAll()              
            .anyRequest().authenticated()
            .and().addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
            .build();
            
        
            
    }
    @Bean
    public AuthenticationManager authenticationManager
    ( AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }    
}
