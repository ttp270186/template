package vn.ttp.template.infra.log.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.server.WebFilter;
import vn.ttp.template.infra.log.aop.LogExecutionTimeAspect;
import vn.ttp.template.infra.log.filter.RequestLoggingFilter;
import vn.ttp.template.infra.log.formatter.DurationFormatter;
import vn.ttp.template.infra.log.formatter.DurationFormatterFactory;

import java.util.List;

@Configuration
@EnableAspectJAutoProxy
public class LoggingConfig {

  @Bean
  public WebFilter requestLoggingFilter() {
    return new RequestLoggingFilter();
  }

  @Bean
  public LogExecutionTimeAspect logExecutionTimeAspect(DurationFormatterFactory durationFormatterFactory) {
    return new LogExecutionTimeAspect(durationFormatterFactory);
  }

  @Bean
  public DurationFormatterFactory durationFormatterFactory(List<DurationFormatter> durationFormatters) {
    return new DurationFormatterFactory(durationFormatters);
  }

}
