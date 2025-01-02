package vn.ttp.template.infra.log;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.web.server.WebFilter;
import vn.ttp.template.infra.log.aop.LogExecutionTimeAspect;
import vn.ttp.template.infra.log.filter.RequestLoggingFilter;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = LoggingConfig.class)
class LoggingConfigTest {

  @Autowired
  private ApplicationContext applicationContext;

  @Test
  void contextLoads() {
    // Check if the RequestLoggingFilter bean is present in the application context
    WebFilter loggingFilter = applicationContext.getBean(RequestLoggingFilter.class);
    assertNotNull(loggingFilter, "RequestLoggingFilter bean should be present in the context");

    // Check if the LogExecutionTimeAspect bean is present in the application context
    LogExecutionTimeAspect logExecutionTimeAspect = applicationContext.getBean(LogExecutionTimeAspect.class);
    assertNotNull(logExecutionTimeAspect, "LogExecutionTimeAspect bean should be present in the context");
  }
}
