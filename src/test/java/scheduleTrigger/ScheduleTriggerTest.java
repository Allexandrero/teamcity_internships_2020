package scheduleTrigger;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ScheduleTriggerTest {
    ScheduleTrigger scheduleTrigger;

    @Before
    public void setUp() {
        scheduleTrigger = new ScheduleTrigger();
    }

    @Test
    public void testShouldExecuteTaskOncePerMinute() throws Exception {
        boolean[] executed = new boolean[1];

        scheduleTrigger.scheduleExecution("0 * * * * *", () -> {
            executed[0] = true;
        });

        Thread.sleep(60_000);
        Assert.assertTrue(executed[0]);
    }
}
