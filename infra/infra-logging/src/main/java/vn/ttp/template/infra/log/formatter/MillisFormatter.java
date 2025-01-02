package vn.ttp.template.infra.log.formatter;

import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * Formatter for durations less than or equal to 1 second, displayed in milliseconds.
 */
@Component
class MillisFormatter implements DurationFormatter {

  @Override
  public String format(Duration duration) {
    long milliseconds = duration.toMillis();  // Directly get milliseconds, no rounding
    return milliseconds + " millisecond" + (milliseconds != 1 ? "s" : "");
  }

  @Override
  public boolean test(Duration duration) {
    // Accept only durations less than or equal to 1 second
    return duration.compareTo(ONE_SECOND) <= 0;
  }
}
