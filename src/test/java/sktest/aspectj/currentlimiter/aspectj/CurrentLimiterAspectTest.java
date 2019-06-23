package sktest.aspectj.currentlimiter.aspectj;

import org.junit.Test;
import sktest.aspectj.currentlimiter.SKUnit;
import sktest.aspectj.currentlimiter.aspectj.prepare.PrepareCurrentLimiterAspect;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CurrentLimiterAspectTest extends SKUnit {

  @Test
  public void around() {
    ExecutorService executorService = Executors.newFixedThreadPool(6);
    for (int i = 0; i < 50; i++) {
      executorService.submit(new PrepareCurrentLimiterAspect());
    }
    executorService.shutdown();
  }
}
