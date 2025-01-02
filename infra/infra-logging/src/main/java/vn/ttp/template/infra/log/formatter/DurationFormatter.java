package vn.ttp.template.infra.log.formatter;

import java.time.Duration;
import java.util.function.Predicate;

/**
 * Interface for formatting Duration objects into human-readable strings. Extends Predicate<Duration> to allow checking
 * applicability of a formatter for a given Duration.
 */
public interface DurationFormatter extends Predicate<Duration> {

  Duration ONE_SECOND = Duration.ofSeconds(1);
  Duration ONE_MINUTE = Duration.ofMinutes(1);
  Duration ONE_HOUR = Duration.ofHours(1);

  /**
   * Formats the given duration into a human-readable string.
   *
   * @param duration the duration to format
   * @return a formatted string representing the duration
   */
  String format(Duration duration);

  /**
   * Tests whether this formatter is applicable to the given duration.
   *
   * @param duration the duration to test
   * @return true if this formatter is applicable, false otherwise
   */
  @Override
  boolean test(Duration duration);
}
