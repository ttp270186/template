package vn.ttp.template.infra.log.formatter;

import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * Default DurationFormatter to handle cases where no specific formatter matches. The class is package-private to
 * restrict access.
 */
@Component
class DefaultDurationFormatter implements DurationFormatter {

  @Override
  public String format(Duration duration) {
    throw new IllegalStateException("Request is handled more than 1 hour by the system.");
  }

  @Override
  public boolean test(Duration duration) {
    return false;  // Always returns false to let other formatters be chosen first
  }
}
