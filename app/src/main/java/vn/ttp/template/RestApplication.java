package vn.ttp.template;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import vn.ttp.template.infra.log.LoggingConfig;
import vn.ttp.template.rest.RestConfig;

@SpringBootApplication
@Import({LoggingConfig.class, RestConfig.class })
public class RestApplication {
  public static void main(String[] args) {
    SpringApplication.run(RestApplication.class, args);
  }
}
