package com.example.jwtdemo.security.service;

import com.example.jwtdemo.security.Audience;
import java.time.LocalDateTime;
import org.springframework.mobile.device.Device;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtTokenService {

  Boolean validateToken(String token, UserDetails userDetails);

  String getUsernameFromToken(String token);

  LocalDateTime getExpirationDateFromToken(String token);

  LocalDateTime getCreatedDateFromToken(String token);

  Audience getAudienceFromToken(String token);

  String generateToken(UserDetails userDetails, Device device);
}
