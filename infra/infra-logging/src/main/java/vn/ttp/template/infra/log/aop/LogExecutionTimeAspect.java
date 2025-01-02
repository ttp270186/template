package vn.ttp.template.infra.log.aop;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import vn.ttp.template.infra.log.formatter.DurationFormatterFactory;

import java.time.Duration;
import java.util.logging.Logger;

@Aspect
@Component
@RequiredArgsConstructor
public class LogExecutionTimeAspect {

  private static final Logger logger = Logger.getLogger(LogExecutionTimeAspect.class.getName());

  private final DurationFormatterFactory durationFormatterFactory;

  // Apply to all methods annotated with @LogExecutionTime in the correct package
  @Around("@annotation(vn.ttp.template.infra.log.LogExecutionTime) ")
  public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
    long startTime = System.currentTimeMillis();
    Object result = null;

    try {
      // Proceed with the method execution
      result = joinPoint.proceed();
    } finally {
      // Ensure logging happens regardless of success or failure
      long endTime = System.currentTimeMillis();
      Duration duration = Duration.ofMillis(endTime - startTime);

      // Get the appropriate DurationFormatter from the factory
      String formattedMessage = durationFormatterFactory.getFormatter(duration).format(duration);

      // Log the execution time
      logger.info(() -> joinPoint.getSignature() + " executed in " + formattedMessage);
    }

    return result;
  }
}
