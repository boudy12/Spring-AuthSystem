package com.security.AuthSystem.service;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		var authourities = authentication.getAuthorities();
		var roles = authourities.stream().map(r -> r.getAuthority()).findFirst();
		
		if(roles.orElse("").equals("ADMIN")) {
			response.sendRedirect("/admin/dashboard");
		}else if (roles.orElse("").equals("USER")) {
			response.sendRedirect("/user/home");
		}else {
			
			response.sendRedirect("/error");
		}
	}
}