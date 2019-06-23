package org.shaneking.aspectj.currentlimiter.exception;

public class CurrentLimiterException extends RuntimeException {
  public CurrentLimiterException() {
    super();
  }

  public CurrentLimiterException(String message) {
    super(message);
  }

  public CurrentLimiterException(String message, Throwable cause) {
    super(message, cause);
  }

  public CurrentLimiterException(Throwable cause) {
    super(cause);
  }
}
