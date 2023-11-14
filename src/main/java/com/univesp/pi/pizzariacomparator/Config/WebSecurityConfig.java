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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;



@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)

public class WebSecurityConfig {
    @Autowired
    private FilterToken filter;

        @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");  // Permitir solicitações de qualquer origem
        configuration.addAllowedMethod("*");  // Permitir todos os métodos HTTP (GET, POST, PUT, DELETE, etc.)
        configuration.addAllowedHeader("*");  // Permitir todos os cabeçalhos

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

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
            .cors()
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
