package vn.ttp.template.infra.log;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.server.WebFilter;
import vn.ttp.template.infra.log.filter.RequestLoggingFilter;

@Configuration
@ComponentScan
@EnableAspectJAutoProxy
public class LoggingConfig {

  @Bean
  public WebFilter requestLoggingFilter() {
    return new RequestLoggingFilter();
  }

}
