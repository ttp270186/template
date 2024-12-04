package vn.ttp.template.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import vn.ttp.template.infra.log.LogExecutionTime;

@Service
public class GreetingService {

  @LogExecutionTime
  public Mono<String> greet(String requestId) {
    // Return a Mono, wrapping the greeting message.
    return Mono.just("Hello, your request ID is: " + requestId);
  }

}
