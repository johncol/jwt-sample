package com.example.jwtdemo.security;

import java.util.Collection;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtUser implements UserDetails {

  private final Long id;
  private final String identification;
  private final Collection<? extends GrantedAuthority> authorities;

  public JwtUser(Long id, String identification, Collection<? extends GrantedAuthority> authorities) {
    this.id = id;
    this.identification = identification;
    this.authorities = authorities;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return identification;
  }

  @Override
  public String getUsername() {
    return identification;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
}
