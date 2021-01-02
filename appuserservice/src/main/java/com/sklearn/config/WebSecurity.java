package com.sklearn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.sklearn.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	
	@Autowired
	Environment env;
	
	@Autowired
	UserService userService;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// In Memory authentication
		// auth.inMemoryAuthentication()
		//	.withUser("user").password(bCryptPasswordEncoder.encode("password")).roles("USER")
		//	.and()
		//	.withUser("admin").password(bCryptPasswordEncoder.encode("admin")).roles("USER", "ADMIN");
		
		auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		// All all API's 
		// http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
		
		// All API's having '/user'
		// http.authorizeRequests().antMatchers("/user/**").permitAll();
		
		// Lock to specific IP
		// http.authorizeRequests().antMatchers("/user/**").hasIpAddress(env.getProperty("zuulgtwayip"));
		
		// Custom authorization
		http.authorizeRequests().antMatchers("/user/**").permitAll().and().addFilter(getAuthFilter());
		
		http.headers().frameOptions().disable();
	}

	private AuthCustomFilter getAuthFilter() throws Exception {
		AuthCustomFilter af = new AuthCustomFilter(userService, env);
		af.setAuthenticationManager(authenticationManager());
		
		// Custom login URL path. If not provided, By default '/login' will be considered.
		af.setFilterProcessesUrl(env.getProperty("login-url"));
		return af;
	}
}
