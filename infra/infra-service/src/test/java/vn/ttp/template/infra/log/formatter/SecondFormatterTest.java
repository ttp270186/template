package vn.ttp.template.infra.log.formatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SecondFormatterTest {

  private SecondFormatter secondFormatter;

  @BeforeEach
  void setUp() {
    secondFormatter = new SecondFormatter();
  }

  @Test
  void format_shouldReturnCorrectFormattedMessage() {
    // Test case 1: Duration of 2 seconds
    Duration duration = Duration.ofSeconds(2);
    String result = secondFormatter.format(duration);
    assertEquals("2 seconds", result);

    // Test case 2: Duration of 1 second
    duration = Duration.ofSeconds(1);
    result = secondFormatter.format(duration);
    assertEquals("1 second", result);
  }

  @Test
  void test_shouldReturnTrueForDurationsBetween1SecondAnd1Minute() {
    // Test case 1: Duration greater than 1 second and less than 1 minute (e.g., 30 seconds)
    Duration duration = Duration.ofSeconds(30);
    assertTrue(secondFormatter.test(duration));

    // Test case 2: Duration equal to 1 second (edge case, should be false)
    duration = Duration.ofSeconds(1);
    assertFalse(secondFormatter.test(duration));

    // Test case 3: Duration equal to 1 minute (edge case, should be false)
    duration = Duration.ofMinutes(1);
    assertFalse(secondFormatter.test(duration));

    // Test case 4: Duration less than 1 second (e.g., 500 milliseconds, should be false)
    duration = Duration.ofMillis(500);
    assertFalse(secondFormatter.test(duration));
  }
}
