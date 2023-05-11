package com.luv2code.springboot.cruddemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Arrays;
import java.util.List;

@Configuration
public class DemoSecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails Josh = User.builder().username("Josh").password("{noop}password").roles("EMPLOYEE").build();
        UserDetails SCOTT = User.builder().username("SCOTT").password("{noop}password").roles("ADMIN","EMPLOYEE").build();
        UserDetails Dreksha = User.builder().username("Dreksha").password("{noop}password").roles("ADMIN","EMPLOYEE","HR").build();
        return new InMemoryUserDetailsManager(Josh, SCOTT, Dreksha);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(Configuration -> Configuration
                .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("HR")
                .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN"));

        httpSecurity
                .httpBasic();

        httpSecurity.csrf().disable();
        return httpSecurity.build();


    }





}





