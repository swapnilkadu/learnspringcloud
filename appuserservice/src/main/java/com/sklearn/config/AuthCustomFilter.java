package com.sklearn.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sklearn.dto.UserDTO;
import com.sklearn.service.UserService;
import com.sklearn.uimodel.LoginReqModel;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthCustomFilter extends UsernamePasswordAuthenticationFilter {
	
	Environment env;
	UserService userService;
	
	public AuthCustomFilter(UserService userService, Environment env) {
		this.env = env;
		this.userService = userService;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			LoginReqModel req = new ObjectMapper().readValue(request.getInputStream(), LoginReqModel.class);
			return getAuthenticationManager().authenticate(
					new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword(), new ArrayList<>()));
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// super.successfulAuthentication(request, response, chain, authResult);
		String usrname = ((User)authResult.getPrincipal()).getUsername();
		UserDTO dto = userService.getUserDetailsByEmail(usrname);
		
		String token = Jwts.builder()
				.setSubject(dto.getUsrId())
				.setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(env.getProperty("token.expire.time"))))
				.signWith(SignatureAlgorithm.HS512, env.getProperty("token.secret"))
				.compact();
		
		response.addHeader("token", token);
		response.addHeader("userid", dto.getUsrId());
	}
}
