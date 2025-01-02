package vn.ttp.template.infra.log.formatter;

import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * Formatter for durations between 1 second and 1 minute, displayed in seconds.
 */
@Component
class SecondFormatter implements DurationFormatter {

  @Override
  public String format(Duration duration) {
    long seconds = duration.getSeconds();  // Directly get seconds, no rounding
    return seconds + " second" + (seconds != 1 ? "s" : "");
  }

  @Override
  public boolean test(Duration duration) {
    // Accept only durations greater than 1 second and less than 1 minute
    return duration.compareTo(ONE_SECOND) > 0 && duration.compareTo(ONE_MINUTE) < 0;
  }
}
