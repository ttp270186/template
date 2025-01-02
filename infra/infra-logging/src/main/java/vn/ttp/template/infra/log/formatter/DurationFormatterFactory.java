package vn.ttp.template.infra.log.formatter;

import java.time.Duration;
import java.util.List;

/**
 * Factory to choose the correct DurationFormatter based on the provided duration. If no formatter matches, returns a
 * default one.
 */
public class DurationFormatterFactory {

  private final List<DurationFormatter> durationFormatters;

  public DurationFormatterFactory(List<DurationFormatter> durationFormatters) {
    this.durationFormatters = durationFormatters;
  }

  /**
   * Returns the appropriate DurationFormatter based on the given duration.
   *
   * @param duration the duration to format
   * @return the appropriate DurationFormatter
   */
  public DurationFormatter getFormatter(Duration duration) {
    return durationFormatters.stream()
        .filter(formatter -> formatter.test(duration))
        .findFirst()
        .orElse(new DefaultDurationFormatter());  // Return the default formatter if no match
  }
}
