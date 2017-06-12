package com.example.jwtdemo.security.service;

import com.example.jwtdemo.security.JwtUserFactory;
import com.example.jwtdemo.security.db.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepository repository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return repository.findOneByUsername(username)
        .map(JwtUserFactory::fromUser)
        .orElseThrow(() -> new UsernameNotFoundException(String.format("User with username %s was not found", username)));
  }
}
