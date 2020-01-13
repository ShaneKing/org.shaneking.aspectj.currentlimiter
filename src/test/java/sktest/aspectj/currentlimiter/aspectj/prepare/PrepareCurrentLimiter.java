package sktest.aspectj.currentlimiter.aspectj.prepare;

import org.shaneking.aspectj.currentlimiter.annotation.CurrentLimiter;
import org.shaneking.skava.util.Date0;

public class PrepareCurrentLimiter {

  @CurrentLimiter(3)
  public void currentLimiter() {
    System.out.println(Date0.on().dateTime());
  }

}
