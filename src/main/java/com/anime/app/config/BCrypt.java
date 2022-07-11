package com.anime.app.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class BCrypt {

  public BCrypt bCrypt() {
    return new BCrypt();
  }
}
