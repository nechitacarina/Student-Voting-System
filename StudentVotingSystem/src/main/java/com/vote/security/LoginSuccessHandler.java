package com.vote.security;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
		Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
		String redirectUrl = request.getContextPath();
		if(userDetails.hasRole("system admin")) {
			redirectUrl += "/system_admin_home";
		}
		if(userDetails.hasRole("voting admin")) {
			redirectUrl += "/voting_admin_home";
		}
		if(userDetails.hasRole("voter")) {
			redirectUrl += "/voter_home";
		}
		response.sendRedirect(redirectUrl);
	}

	
}
