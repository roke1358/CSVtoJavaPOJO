package com.example.snippet.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class AppConfig {

  @Value("${input.file.person}")
  private String input;

  public AppConfig() {
  }

  @Bean
  public String getInput() {
    return input;
  }

  public void setInput(String input) {
    this.input = input;
  }
}
