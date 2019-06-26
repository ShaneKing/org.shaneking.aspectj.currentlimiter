package sktest.aspectj.currentlimiter.aspectj;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.shaneking.skava.ling.util.List0;
import sktest.aspectj.currentlimiter.SKUnit;
import sktest.aspectj.currentlimiter.aspectj.prepare.PrepareCurrentLimiterAspect;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
public class CurrentLimiterAspectTest extends SKUnit {

  @Test
  public void around() throws InterruptedException {
    ExecutorService executorService = Executors.newFixedThreadPool(6);
    List<Future<Boolean>> rstList = executorService.invokeAll(List0.fillList(Lists.newArrayList(), 10, () -> new PrepareCurrentLimiterAspect()));
    Assert.assertEquals(3, rstList.parallelStream().filter(future -> {
      try {
        return future.get();
      } catch (Exception e) {
        log.error(e.getMessage(), e);
        return false;
      }
    }).count());
  }
}
