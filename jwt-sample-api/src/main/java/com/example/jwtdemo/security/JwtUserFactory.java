package com.example.jwtdemo.security;

import com.example.jwtdemo.security.db.User;
import java.util.Arrays;
import java.util.List;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class JwtUserFactory {

  public static final String ROLE_USER = "ROLE_USER";

  public static JwtUser fromUser(User user) {
    return new JwtUser(user.getId(), user.getUsername(), defaultAuthorities());
  }

  private static List<SimpleGrantedAuthority> defaultAuthorities() {
    return Arrays.asList(new SimpleGrantedAuthority(ROLE_USER));
  }

}
