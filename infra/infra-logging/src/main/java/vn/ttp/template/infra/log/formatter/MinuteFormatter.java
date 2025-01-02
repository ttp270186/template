package vn.ttp.template.infra.log.formatter;

import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * Formatter for durations between 1 minute and 1 hour, displayed in minutes.
 */
@Component
class MinuteFormatter implements DurationFormatter {

  @Override
  public String format(Duration duration) {
    long minutes = duration.toMinutes();  // Directly get minutes, no rounding
    return minutes + " minute" + (minutes != 1 ? "s" : "");
  }

  @Override
  public boolean test(Duration duration) {
    // Accept only durations greater than or equal to 1 minute and less than 1 hour
    return duration.compareTo(ONE_MINUTE) >= 0 && duration.compareTo(ONE_HOUR) < 0;
  }
}
