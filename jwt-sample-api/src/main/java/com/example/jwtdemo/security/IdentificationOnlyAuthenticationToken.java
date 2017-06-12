package com.example.jwtdemo.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class IdentificationOnlyAuthenticationToken extends UsernamePasswordAuthenticationToken {

  public IdentificationOnlyAuthenticationToken(Object principal) {
    super(principal, principal);
  }

}