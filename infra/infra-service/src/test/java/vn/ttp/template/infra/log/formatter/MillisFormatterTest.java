package vn.ttp.template.infra.log.formatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MillisFormatterTest {

  private MillisFormatter millisFormatter;

  @BeforeEach
  void setUp() {
    millisFormatter = new MillisFormatter();
  }

  @Test
  void testFormat_WhenDurationIsLessThanOneSecond() {
    // Given a duration of 500 milliseconds
    Duration duration = Duration.ofMillis(500);

    // When
    String result = millisFormatter.format(duration);

    // Then
    assertEquals("500 milliseconds", result);
  }

  @Test
  void testFormat_WhenDurationIsOneSecond() {
    // Given a duration of 1 second
    Duration duration = Duration.ofSeconds(1);

    // When
    String result = millisFormatter.format(duration);

    // Then
    assertEquals("1000 milliseconds", result);
  }

  @Test
  void testFormat_WhenDurationIsGreaterThanOneSecond() {
    // Given a duration of 1.5 seconds
    Duration duration = Duration.ofMillis(1500);

    // When
    String result = millisFormatter.format(duration);

    // Then
    assertEquals("1500 milliseconds", result);
  }

  @Test
  void testTest_WhenDurationIsLessThanOneSecond() {
    // Given a duration of 500 milliseconds
    Duration duration = Duration.ofMillis(500);

    // When
    boolean result = millisFormatter.test(duration);

    // Then
    assertTrue(result);
  }

  @Test
  void testTest_WhenDurationIsEqualToOneSecond() {
    // Given a duration of 1 second
    Duration duration = Duration.ofSeconds(1);

    // When
    boolean result = millisFormatter.test(duration);

    // Then
    assertTrue(result);
  }

  @Test
  void testTest_WhenDurationIsGreaterThanOneSecond() {
    // Given a duration of 1.5 seconds
    Duration duration = Duration.ofMillis(1500);

    // When
    boolean result = millisFormatter.test(duration);

    // Then
    assertFalse(result);
  }
}
