package com.example.jwtdemo.security.service.impl;

import com.example.jwtdemo.security.Audience;
import com.example.jwtdemo.security.JwtProperties;
import com.example.jwtdemo.security.JwtUser;
import com.example.jwtdemo.security.service.JwtTokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtTokenServiceImpl implements JwtTokenService {

  private static final String CLAIM_KEY_USERNAME = "sub";
  private static final String CLAIM_KEY_AUDIENCE = "audience";
  private static final String CLAIM_KEY_CREATED = "created";

  @Autowired
  private JwtProperties properties;

  @Override
  public Boolean validateToken(String token, UserDetails userDetails) {
    JwtUser user = (JwtUser) userDetails;
    final String username = getUsernameFromToken(token);
    return username.equals(user.getUsername()) && !isTokenExpired(token);
  }

  @Override
  public String getUsernameFromToken(String token) {
    try {
      final Claims claims = getClaimsFromToken(token);
      return claims.getSubject();
    } catch (Exception e) {
      return null;
    }
  }

  @Override
  public LocalDateTime getExpirationDateFromToken(String token) {
    try {
      final Claims claims = getClaimsFromToken(token);
      return fromInstant(claims.getExpiration().toInstant());
    } catch (Exception e) {
      return null;
    }
  }

  @Override
  public LocalDateTime getCreatedDateFromToken(String token) {
    try {
      final Claims claims = getClaimsFromToken(token);
      Long millis = (Long) claims.get(CLAIM_KEY_CREATED);
      return fromInstant(Instant.ofEpochMilli(millis));
    } catch (Exception e) {
      return null;
    }
  }

  @Override
  public Audience getAudienceFromToken(String token) {
    try {
      final Claims claims = getClaimsFromToken(token);
      String audience = (String) claims.get(CLAIM_KEY_AUDIENCE);
      return Audience.valueOf(audience);
    } catch (Exception e) {
      return null;
    }
  }

  @Override
  public String generateToken(UserDetails userDetails, Device device) {
    Map<String, Object> claims = new HashMap<>();
    claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
    claims.put(CLAIM_KEY_AUDIENCE, generateAudience(device).toString());
    claims.put(CLAIM_KEY_CREATED, LocalDateTime.now().atZone(zoneId()).toInstant().toEpochMilli());
    return generateToken(claims);
  }

  String generateToken(Map<String, Object> claims) {
    return Jwts.builder()
        .setClaims(claims)
        .setExpiration(toDate(generateExpirationDate()))
        .signWith(SignatureAlgorithm.HS512, properties.getSecret())
        .compact();
  }

  private boolean isTokenExpired(String token) {
    LocalDateTime expirationDate = getExpirationDateFromToken(token);
    return expirationDate.isBefore(LocalDateTime.now());
  }

  private LocalDateTime generateExpirationDate() {
    Instant instant = Instant.ofEpochMilli(System.currentTimeMillis() + properties.getExpiration() * 1000);
    return fromInstant(instant);
  }

  private Audience generateAudience(Device device) {
    if (device.isNormal()) {
      return Audience.WEB;
    } else if (device.isTablet()) {
      return Audience.TABLET;
    } else if (device.isMobile()) {
      return Audience.MOBILE;
    }
    return Audience.UNKNOWN;
  }

  private Claims getClaimsFromToken(String token) {
    try {
      return Jwts.parser()
          .setSigningKey(properties.getSecret())
          .parseClaimsJws(token)
          .getBody();
    } catch (Exception e) {
      return null;
    }
  }

  private LocalDateTime fromInstant(Instant instant) {
    return instant.atZone(zoneId()).toLocalDateTime();
  }

  private Date toDate(LocalDateTime localDateTime) {
    long millis = localDateTime.atZone(zoneId()).toInstant().toEpochMilli();
    return new Date(millis);
  }

  private ZoneId zoneId() {
    return ZoneId.systemDefault();
  }
}