package vn.ttp.template.infra.log.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

public class RequestLoggingFilter implements WebFilter {

  private static final Logger logger = LoggerFactory.getLogger(RequestLoggingFilter.class);

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
    // Get the request ID from headers or create a new one if not available
    String requestId = exchange.getRequest().getHeaders().getFirst("X-Request-Id");
    if (!StringUtils.hasText(requestId)) {
      requestId = "N/A"; // Default value if no ID is provided
    }

    // Set the request ID in MDC for logging correlation
    MDC.put("requestId", requestId);

    // Log the request details
    logger.info("Incoming request: {} {} | Request ID: {}",
        exchange.getRequest().getMethod(),
        exchange.getRequest().getURI(),
        requestId);

    // Continue the request processing chain
    return chain.filter(exchange)
        .doFinally(signalType -> MDC.clear()); // Clear MDC after request processing
  }
}
