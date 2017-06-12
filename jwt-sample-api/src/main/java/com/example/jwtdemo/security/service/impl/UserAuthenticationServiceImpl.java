package com.example.jwtdemo.security.service.impl;

import com.example.jwtdemo.controller.model.JwtAuthenticationResponse;
import com.example.jwtdemo.security.IdentificationOnlyAuthenticationToken;
import com.example.jwtdemo.security.service.JwtTokenService;
import com.example.jwtdemo.security.service.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private JwtTokenService jwtTokenService;

  @Override
  public JwtAuthenticationResponse authenticate(String identification, Device device) throws AuthenticationException {
    IdentificationOnlyAuthenticationToken authToken = new IdentificationOnlyAuthenticationToken(identification);
    Authentication authentication = authenticationManager.authenticate(authToken);
    SecurityContextHolder.getContext().setAuthentication(authentication);
    UserDetails userDetails = userDetailsService.loadUserByUsername(identification);
    String jwtToken = jwtTokenService.generateToken(userDetails, device);
    return new JwtAuthenticationResponse(jwtToken);
  }

}
