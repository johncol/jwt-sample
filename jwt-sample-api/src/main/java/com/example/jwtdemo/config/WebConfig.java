package com.example.jwtdemo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebMvc
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

  private static final Logger logger = LoggerFactory.getLogger(WebConfig.class);

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    logger.info("Setting up CORs");
    registry.addMapping("/**")
        .allowedOrigins("http://localhost:4200", "*")
        .allowedMethods("GET", "POST", "PUT", "OPTIONS")
        .allowedHeaders("*");
  }

}
