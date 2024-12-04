package vn.ttp.template.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import vn.ttp.template.service.GreetingService;

import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class GreetingControllerTest {

  @InjectMocks
  private GreetingController greetingController;

  @Mock
  private GreetingService greetingService;

  @Test
  void greet_withRequestId_returnsGreetingMessage() {
    // Arrange
    String message = "Hello, your request ID is: 12345";
    String requestId = "any request Id";
    // Assume
    doReturn(Mono.just(message)).when(greetingService).greet(requestId);

    // Act
    Mono<String> result = greetingController.greet(requestId);

    // Assert using StepVerifier
    StepVerifier.create(result)
        .expectNext(message)  // Expect the greeting message with requestId
        .expectComplete()  // Expect the Mono to complete
        .verify();  // Trigger the verification
  }
}
