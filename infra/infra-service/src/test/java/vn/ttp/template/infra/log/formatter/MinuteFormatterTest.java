package vn.ttp.template.infra.log.formatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MinuteFormatterTest {

  private MinuteFormatter minuteFormatter;

  @BeforeEach
  void setUp() {
    minuteFormatter = new MinuteFormatter();
  }

  @Test
  void testFormat_WhenDurationIsExactlyOneMinute() {
    // Given a duration of 1 minute
    Duration duration = Duration.ofMinutes(1);

    // When
    String result = minuteFormatter.format(duration);

    // Then
    assertEquals("1 minute", result);
  }

  @Test
  void testFormat_WhenDurationIsLessThanOneMinute() {
    // Given a duration of 30 seconds
    Duration duration = Duration.ofSeconds(30);

    // When
    String result = minuteFormatter.format(duration);

    // Then
    assertEquals("0 minutes", result);  // Since it's less than 1 minute, it will show 0 minutes.
  }

  @Test
  void testFormat_WhenDurationIsGreaterThanOneMinute() {
    // Given a duration of 10 minutes
    Duration duration = Duration.ofMinutes(10);

    // When
    String result = minuteFormatter.format(duration);

    // Then
    assertEquals("10 minutes", result);
  }

  @Test
  void testFormat_WhenDurationIsExactlyOneHour() {
    // Given a duration of 1 hour (should not be accepted)
    Duration duration = Duration.ofHours(1);

    // When
    String result = minuteFormatter.format(duration);

    // Then
    assertEquals("60 minutes", result);  // It will format to minutes even if it's exactly one hour.
  }

  @Test
  void testTest_WhenDurationIsExactlyOneMinute() {
    // Given a duration of 1 minute
    Duration duration = Duration.ofMinutes(1);

    // When
    boolean result = minuteFormatter.test(duration);

    // Then
    assertTrue(result);  // 1 minute should be accepted.
  }

  @Test
  void testTest_WhenDurationIsLessThanOneMinute() {
    // Given a duration of 30 seconds
    Duration duration = Duration.ofSeconds(30);

    // When
    boolean result = minuteFormatter.test(duration);

    // Then
    assertFalse(result);  // Should not be accepted, as it's less than 1 minute.
  }

  @Test
  void testTest_WhenDurationIsGreaterThanOneMinuteAndLessThanOneHour() {
    // Given a duration of 45 minutes
    Duration duration = Duration.ofMinutes(45);

    // When
    boolean result = minuteFormatter.test(duration);

    // Then
    assertTrue(result);  // Should be accepted since it's between 1 minute and 1 hour.
  }

  @Test
  void testTest_WhenDurationIsGreaterThanOneHour() {
    // Given a duration of 1 hour and 15 minutes
    Duration duration = Duration.ofMinutes(75);

    // When
    boolean result = minuteFormatter.test(duration);

    // Then
    assertFalse(result);  // Should not be accepted, as it's more than 1 hour.
  }
}
