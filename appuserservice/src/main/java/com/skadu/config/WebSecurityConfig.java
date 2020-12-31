package com.skadu.config;

import javax.servlet.Filter;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
    protected void configure(HttpSecurity http) throws Exception { 
        http
        .cors().and()
        .csrf().disable().authorizeRequests()
        .antMatchers("/createuser").hasRole("manager")
        .anyRequest().authenticated()
        .and()
        .addFilter(getAuthFilter());
//        .and()
//        .formLogin();
    }

	private AuthFilter getAuthFilter() throws Exception {
		AuthFilter af =  new AuthFilter();
		af.setAuthenticationManager(authenticationManager());
		return af;
	}

	
}
