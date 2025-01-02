package vn.ttp.template.infra.log.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import vn.ttp.template.infra.log.formatter.DurationFormatter;
import vn.ttp.template.infra.log.formatter.DurationFormatterFactory;

import java.time.Duration;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LogExecutionTimeAspectTest {

  @Mock
  private ProceedingJoinPoint joinPoint;

  @Mock
  private DurationFormatterFactory durationFormatterFactory;

  @InjectMocks
  private LogExecutionTimeAspect logExecutionTimeAspect;

  @BeforeEach
  void setUp() {
    // Initialize the mock logger
    logExecutionTimeAspect = new LogExecutionTimeAspect(durationFormatterFactory);
  }

  @Test
  void testLogExecutionTime_Success() throws Throwable {
    // Prepare mock data
    Object returnValue = "Test Result";
    when(joinPoint.proceed()).thenReturn(returnValue);
    DurationFormatter mockFormatter = mock(DurationFormatter.class);

    // Prepare duration formatter mock
    String formattedMessage = "500 ms";
    when(mockFormatter.format(any(Duration.class))).thenReturn(formattedMessage);
    when(durationFormatterFactory.getFormatter(any(Duration.class))).thenReturn(mockFormatter);

    // Call the method to test
    logExecutionTimeAspect.logExecutionTime(joinPoint);

    // Verify that the proceed method was called
    verify(joinPoint).proceed();

    // Verify that the logging happened
    verify(durationFormatterFactory).getFormatter(any(Duration.class));
  }

  @Test
  void testLogExecutionTime_ThrowsException() throws Throwable {
    // Prepare to throw an exception
    when(joinPoint.proceed()).thenThrow(new RuntimeException("Test Exception"));

    // Call the method to test
    try {
      logExecutionTimeAspect.logExecutionTime(joinPoint);
    } catch (RuntimeException e) {
      // Expected exception, test should continue
    }

    // Verify that the proceed method was called
    verify(joinPoint).proceed();

  }
}
