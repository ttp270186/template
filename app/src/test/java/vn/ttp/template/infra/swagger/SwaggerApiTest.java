package vn.ttp.template.infra.swagger;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SwaggerApiTest {

  @Autowired
  private WebTestClient webTestClient;

  @Test
  void testSwaggerApiDocsEndpoint() {
    webTestClient.get()
        .uri("/v3/api-docs")
        .exchange()
        .expectStatus().isOk()
        .expectHeader().contentType("application/json")
        .expectBody()
        .jsonPath("$.openapi").isEqualTo("3.0.1") // Check OpenAPI version
        .jsonPath("$.info.title").isNotEmpty(); // Check API title is defined
  }

  @Test
  void testSwaggerUiEndpoint() {
    webTestClient.get()
        .uri("/webjars/swagger-ui/index.html")
        .exchange()
        .expectStatus().isOk()
        .expectHeader().contentType("text/html"); // Ensure Swagger UI page loads
  }
}
