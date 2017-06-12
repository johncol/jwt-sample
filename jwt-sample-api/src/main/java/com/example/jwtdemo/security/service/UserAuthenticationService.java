package com.example.jwtdemo.security.service;

import com.example.jwtdemo.controller.model.JwtAuthenticationResponse;
import org.springframework.mobile.device.Device;
import org.springframework.security.core.AuthenticationException;

public interface UserAuthenticationService {

  JwtAuthenticationResponse authenticate(String identification, Device device) throws AuthenticationException;
}
