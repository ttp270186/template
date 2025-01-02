package vn.ttp.template.infra.log.filter;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilterChain;
import org.springframework.web.server.adapter.DefaultServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RequestLoggingFilterTest {

  @Mock
  private WebFilterChain filterChain;

  private RequestLoggingFilter requestLoggingFilter;
  private ListAppender<ILoggingEvent> listAppender;

  @BeforeEach
  void setUp() {
    requestLoggingFilter = new RequestLoggingFilter();

    Logger logger = (Logger) LoggerFactory.getLogger(RequestLoggingFilter.class);
    listAppender = new ListAppender<>();
    listAppender.start();
    logger.addAppender(listAppender);
  }

  @Test
  void testFilterLogsRequestWithRequestId() {
    // Mock request with "X-Request-Id" header
    String requestId = "123e4567-e89b-12d3-a456-426614174123";
    MockServerHttpRequest request = MockServerHttpRequest.get("/test")
        .header("X-Request-Id", requestId)
        .build();
    ServerWebExchange exchange = mock(DefaultServerWebExchange.class);

    // Mock filter chain behavior
    when(filterChain.filter(exchange)).thenReturn(Mono.empty());
    when(exchange.getRequest()).thenReturn(request);

    // Execute the filter
    requestLoggingFilter.filter(exchange, filterChain).block();

    // Assertions
    verify(filterChain).filter(exchange); // Ensure the filter chain is invoked

    // Clean up MDC
    MDC.clear();

    // Verify that the log contains the request ID
    List<ILoggingEvent> logsList = listAppender.list;
    assertThat(logsList).hasSize(1);
    assertThat(logsList.get(0).getFormattedMessage()).contains("Request ID: " + requestId);
  }

  @Test
  void testFilterLogsRequestWithoutRequestId() {
    // Mock request without "X-Request-Id" header
    MockServerHttpRequest request = MockServerHttpRequest.get("/test").build();
    ServerWebExchange exchange = mock(DefaultServerWebExchange.class);

    // Mock filter chain behavior
    when(filterChain.filter(exchange)).thenReturn(Mono.empty());
    when(exchange.getRequest()).thenReturn(request);

    // Execute the filter
    requestLoggingFilter.filter(exchange, filterChain).block();

    // Assertions
    verify(filterChain).filter(exchange); // Ensure the filter chain is invoked

    // Clean up MDC
    MDC.clear();

    // Verify that the log contains the default value "N/A"
    List<ILoggingEvent> logsList = listAppender.list;
    assertThat(logsList).hasSize(1);
    assertThat(logsList.get(0).getFormattedMessage()).contains("Request ID: N/A");
  }
}
