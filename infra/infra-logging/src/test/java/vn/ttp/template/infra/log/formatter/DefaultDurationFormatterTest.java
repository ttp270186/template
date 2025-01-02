package vn.ttp.template.infra.log.formatter;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DefaultDurationFormatterTest {

  private final DefaultDurationFormatter defaultFormatter = new DefaultDurationFormatter();

  @Test
  void testTestMethodAlwaysReturnsFalse() {
    // Given various duration values
    Duration lessThanAnHour = Duration.ofMinutes(30);
    Duration moreThanAnHour = Duration.ofHours(2);
    Duration exactlyAnHour = Duration.ofHours(1);

    // Then test method always returns false
    assertFalse(defaultFormatter.test(lessThanAnHour));
    assertFalse(defaultFormatter.test(moreThanAnHour));
    assertFalse(defaultFormatter.test(exactlyAnHour));
  }

  @Test
  void testFormatThrowsException() {
    // Given any duration
    Duration anyDuration = Duration.ofHours(2);

    // When calling format, expect IllegalStateException
    IllegalStateException exception = assertThrows(
        IllegalStateException.class,
        () -> defaultFormatter.format(anyDuration)
    );

    // Then the exception message is as expected
    assertEquals("Request is handled more than 1 hour by the system.", exception.getMessage());
  }
}
