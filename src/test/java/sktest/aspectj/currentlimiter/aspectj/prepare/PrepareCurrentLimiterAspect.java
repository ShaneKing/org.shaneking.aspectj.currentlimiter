package sktest.aspectj.currentlimiter.aspectj.prepare;

public class PrepareCurrentLimiterAspect implements Runnable {

  @Override
  public void run() {
    try {
      new PrepareCurrentLimiter().currentLimiter();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
