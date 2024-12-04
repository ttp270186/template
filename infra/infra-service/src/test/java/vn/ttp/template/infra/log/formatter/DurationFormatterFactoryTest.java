package vn.ttp.template.infra.log.formatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

class DurationFormatterFactoryTest {

  private DurationFormatter lessThanMinuteFormatter;
  private DurationFormatter greaterThanMinuteFormatter;
  private DefaultDurationFormatter defaultFormatter;
  private DurationFormatterFactory factory;

  @BeforeEach
  void setUp() {
    // Mock formatters
    lessThanMinuteFormatter = mock(DurationFormatter.class);
    greaterThanMinuteFormatter = mock(DurationFormatter.class);
    defaultFormatter = mock(DefaultDurationFormatter.class);

    // Configure mocks
    when(lessThanMinuteFormatter.test(Duration.ofSeconds(30))).thenReturn(true);
    when(greaterThanMinuteFormatter.test(Duration.ofMinutes(5))).thenReturn(true);
    when(defaultFormatter.test(any(Duration.class))).thenReturn(false);

    // Initialize factory with mocks
    factory = new DurationFormatterFactory(
        List.of(lessThanMinuteFormatter, greaterThanMinuteFormatter, defaultFormatter));
  }

  @Test
  void shouldReturnMatchingFormatter() {
    // Given a duration that matches lessThanMinuteFormatter
    Duration duration = Duration.ofSeconds(30);

    // When
    DurationFormatter formatter = factory.getFormatter(duration);

    // Then
    assertSame(lessThanMinuteFormatter, formatter);
    verify(lessThanMinuteFormatter).test(duration);
    verifyNoInteractions(defaultFormatter);
  }

  @Test
  void shouldReturnDefaultFormatterWhenNoOtherMatches() {
    // Given a duration that matches no specific formatter
    Duration duration = Duration.ofHours(2);

    // When
    DurationFormatter formatter = factory.getFormatter(duration);

    // Then
    assertTrue(formatter instanceof DefaultDurationFormatter); // Updated to check instance type
    verify(defaultFormatter).test(duration);
    verify(lessThanMinuteFormatter).test(duration);
    verify(greaterThanMinuteFormatter).test(duration);
  }

  @Test
  void shouldStopAtFirstMatchingFormatter() {
    // Given a duration that matches both lessThanMinuteFormatter and greaterThanMinuteFormatter
    Duration duration = Duration.ofSeconds(30);
    when(greaterThanMinuteFormatter.test(duration)).thenReturn(true);

    // When
    DurationFormatter formatter = factory.getFormatter(duration);

    // Then
    assertSame(lessThanMinuteFormatter, formatter); // Should pick the first match
    verify(lessThanMinuteFormatter).test(duration);
    verifyNoMoreInteractions(greaterThanMinuteFormatter);
  }
}
