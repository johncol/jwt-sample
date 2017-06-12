package com.example.jwtdemo.security;

import com.example.jwtdemo.controller.RequestController;
import com.example.jwtdemo.security.service.JwtTokenService;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

  private static final Logger logger = LoggerFactory.getLogger(RequestController.class);

  private static final String PREFIX = "Bearer ";

  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private JwtTokenService jwtTokenService;

  @Autowired
  private JwtProperties properties;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
    String authToken = getAuthToken(request.getHeader(properties.getHeader()));
    String username = jwtTokenService.getUsernameFromToken(authToken);
    logger.info("Checking authentication for user {}", username);

    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
      if (jwtTokenService.validateToken(authToken, userDetails)) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    }

    chain.doFilter(request, response);
  }

  private String getAuthToken(String header) {
    String authToken = null;
    if (header != null && header.startsWith(PREFIX)) {
      authToken = header.substring(PREFIX.length());
    }
    return authToken;
  }
}