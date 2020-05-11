package sktest.aspectj.currentlimiter;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import sktest.aspectj.currentlimiter.aspectj.CurrentLimiterAspectTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({CurrentLimiterAspectTest.class})
public class CurrentLimiterSuites {
}
