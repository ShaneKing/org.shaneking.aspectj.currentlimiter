package org.shaneking.aspectj.currentlimiter.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.shaneking.aspectj.currentlimiter.annotation.CurrentLimiter;
import org.shaneking.aspectj.currentlimiter.exception.CurrentLimiterException;
import org.shaneking.skava.util.concurrent.atomic.AtomicLong0;

import java.util.concurrent.atomic.AtomicLong;

@Aspect
public class CurrentLimiterAspect {

  @Pointcut("execution(@org.shaneking.aspectj.currentlimiter.annotation.CurrentLimiter * *..*.*(..))")
  private void pointcut() {
  }

  @Around("pointcut() && @annotation(currentLimiter)")
  public Object aroundCurrentLimiter(ProceedingJoinPoint joinPoint, CurrentLimiter currentLimiter) throws Throwable {
    String key = joinPoint.getSignature().toLongString();
    if (AtomicLong0.tryDecreaseFailed(initAtomicLong(key, currentLimiter), 0, 1)) {
      try {
        return joinPoint.proceed();
      } finally {
        AtomicLong0.tryIncreaseFailed(CurrentLimiterConstant.BUCKET_MAP.get(key), currentLimiter.value(), 1);
      }
    } else {
      throw new CurrentLimiterException(String.valueOf(CurrentLimiterConstant.BUCKET_MAP.get(key).longValue()));
    }
  }

  private AtomicLong initAtomicLong(String key, CurrentLimiter currentLimiter) {
    if (!CurrentLimiterConstant.BUCKET_MAP.containsKey(key)) {
      synchronized (this) {
        if (!CurrentLimiterConstant.BUCKET_MAP.containsKey(key)) {
          CurrentLimiterConstant.BUCKET_MAP.put(key, new AtomicLong(currentLimiter.value()));
        }
      }
    }
    return CurrentLimiterConstant.BUCKET_MAP.get(key);
  }
}
