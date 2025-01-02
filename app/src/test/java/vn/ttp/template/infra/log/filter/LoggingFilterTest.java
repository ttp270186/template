package vn.ttp.template.infra.log.filter;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import vn.ttp.template.infra.log.aop.LogExecutionTimeAspect;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@WebFluxTest
@ActiveProfiles("test")
class LoggingFilterTest {

  @Autowired
  private WebTestClient webTestClient;
  private ListAppender<ILoggingEvent> requestAppender;
  private ListAppender<ILoggingEvent> executionAppender;

  @BeforeEach
  void setup() {
    Logger requestLogger = (Logger) LoggerFactory.getLogger(RequestLoggingFilter.class);
    requestAppender = new ListAppender<>();
    requestAppender.start();
    requestLogger.addAppender(requestAppender);

    Logger executionLogger = (Logger) LoggerFactory.getLogger(LogExecutionTimeAspect.class);
    executionAppender = new ListAppender<>();
    executionAppender.start();
    executionLogger.addAppender(executionAppender);
  }

  @Test
  void testLoggingFilterWithRequestId() {
    String requestId = "123e4567-e89b-12d3-a456-426614174000";
    webTestClient.get().uri("/api/greet").header("X-Request-Id", requestId)
        .exchange().expectStatus().isOk();

    // Verify that the log contains the request ID
    List<ILoggingEvent> requestLogsList = requestAppender.list; assertThat(requestLogsList).hasSize(1);
    assertThat(requestLogsList.get(0).getFormattedMessage()).contains("Request ID: " + requestId);

    List<ILoggingEvent> executionLogsList = executionAppender.list; assertThat(executionLogsList).hasSize(1);
    assertThat(executionLogsList.get(0).getFormattedMessage()).contains("Mono vn.ttp.template.service.GreetingService.greet(String) executed in ");
  }

  @Test
  void testLoggingFilterWithoutRequestId() {
    webTestClient.get().uri("/api/greet").exchange().expectStatus().isOk();

    // Verify that the log contains the default value "N/A"
    List<ILoggingEvent> requestLogsList = requestAppender.list; assertThat(requestLogsList).hasSize(1);
    assertThat(requestLogsList.get(0).getFormattedMessage()).contains("Request ID: N/A");

    List<ILoggingEvent> executionLogsList = executionAppender.list; assertThat(executionLogsList).hasSize(1);
    assertThat(executionLogsList.get(0).getFormattedMessage()).contains("Mono vn.ttp.template.service.GreetingService.greet(String) executed in ");
  }
}
