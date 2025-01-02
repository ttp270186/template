package vn.ttp.template;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LocalRestApplication {
  public static void main(String[] args) {
    // Configure the application runs on local mode.
    System.setProperty("spring.profiles.active", "local");
    SpringApplication.run(RestApplication.class, args);
  }
}
