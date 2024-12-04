package vn.ttp.template.rest;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest
class GreetingControllerTest {

  @Autowired
  private WebTestClient webTestClient;

  @Test
  void testGreetingWithRequestId() {
    final String requestId = "123e4567-e89b-12d3-a456-426614174000";

    webTestClient.get()
        .uri("/api/greet")
        .header(HttpHeaders.CONTENT_TYPE, "application/json")
        .header("X-Request-Id", requestId)
        .exchange()
        .expectStatus().isOk()
        .expectBody(String.class)
        .value(response -> AssertionsForClassTypes.assertThat(response)
            .isEqualTo("Hello, your request ID is: " + requestId));
  }

  @Test
  void testGreetingWithDefaultRequestId() {
    webTestClient.get()
        .uri("/api/greet")
        .header(HttpHeaders.CONTENT_TYPE, "application/json")
        .exchange()
        .expectStatus().isOk()
        .expectBody(String.class)
        .value(response -> AssertionsForClassTypes.assertThat(response).isEqualTo("Hello, your request ID is: N/A"));
  }

}
