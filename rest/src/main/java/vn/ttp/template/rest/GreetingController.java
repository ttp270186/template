package vn.ttp.template.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import vn.ttp.template.service.GreetingService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class GreetingController {

  private final GreetingService greetingService;

  @GetMapping("/greet")
  public Mono<String> greet(
      @RequestHeader(value = "X-Request-Id", required = false, defaultValue = "N/A") String requestId) {
    // Return a Mono, wrapping the greeting message.
    return greetingService.greet(requestId);
  }
}
