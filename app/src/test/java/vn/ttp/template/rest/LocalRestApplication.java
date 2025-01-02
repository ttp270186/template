package vn.ttp.template.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import vn.ttp.template.infra.log.LoggingConfig;

@SpringBootApplication
@Import({RestConfig.class, LoggingConfig.class })
public class LocalRestApplication {
  public static void main(String[] args) {
    // Configure the application runs on local mode.
    System.setProperty("spring.profiles.active", "local");
    SpringApplication.run(LocalRestApplication.class, args);
  }
}
