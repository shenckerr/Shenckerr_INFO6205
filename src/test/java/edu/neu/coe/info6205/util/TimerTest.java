package edu.neu.coe.info6205.util;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TimerTest {

    @Before
    public void setup() {
        pre = 0;
        run = 0;
        post = 0;
    }

    @Test
    public void testStop() {
        final Timer timer = new Timer();
        GoToSleep(ONETH, 0);
        final double time = timer.stop();
        assertEquals(ONETH, time, 100);
        assertEquals(1, run);
        assertEquals(1, new PrivateMethodTester(timer).invokePrivate("getLaps"));
    }

    @Test
    public void testPauseAndLap() {
        final Timer timer = new Timer();
        final PrivateMethodTester privateMethodTester = new PrivateMethodTester(timer);
        GoToSleep(ONETH, 0);
        timer.pauseAndLap();
        final Long ticks = (Long) privateMethodTester.invokePrivate("getTicks");
        assertEquals(ONETH, ticks / 1e6, 20);
        assertFalse((Boolean) privateMethodTester.invokePrivate("isRunning"));
        assertEquals(1, privateMethodTester.invokePrivate("getLaps"));
    }

    @Test
    public void testPauseAndLapResume0() {
        final Timer timer = new Timer();
        final PrivateMethodTester privateMethodTester = new PrivateMethodTester(timer);
        GoToSleep(TENTH, 0);
        timer.pauseAndLap();
        timer.resume();
        assertTrue((Boolean) privateMethodTester.invokePrivate("isRunning"));
        assertEquals(1, privateMethodTester.invokePrivate("getLaps"));
    }

    @Test
    public void testPauseAndLapResume1() {
        final Timer timer = new Timer();
        GoToSleep(ONETH, 0);
        timer.pauseAndLap();
        GoToSleep(ONETH, 0);
        timer.resume();
        GoToSleep(ONETH, 0);
        final double time = timer.stop();
        assertEquals(ONETH, time, 100.0);
        assertEquals(3, run);
    }

    @Test
    public void testLap() {
        final Timer timer = new Timer();
        GoToSleep(ONETH, 0);
        timer.lap();
        GoToSleep(ONETH, 0);
        final double time = timer.stop();
        assertEquals(ONETH, time, 100.0);
        assertEquals(2, run);
    }

    @Test
    public void testPause() {
        final Timer timer = new Timer();
        GoToSleep(ONETH, 0);
        timer.pause();
        GoToSleep(ONETH, 0);
        timer.resume();
        final double time = timer.stop();
        assertEquals(ONETH, time, 100.0);
        assertEquals(2, run);
    }

    @Test
    public void testMillisecs() {
        final Timer timer = new Timer();
        GoToSleep(TENTH, 0);
        timer.stop();
        final double time = timer.millisecs();
        assertEquals(TENTH_DOUBLE, time, 20.0);
        assertEquals(1, run);
    }

    @Test
    public void testRepeat1() {
        final Timer timer = new Timer();
        final double mean = timer.repeat(250, () -> {
            GoToSleep(TENTH, 0);

            return null;
        });
        assertEquals(250, new PrivateMethodTester(timer).invokePrivate("getLaps"));
        assertEquals(TENTH_DOUBLE, mean, 20);
        assertEquals(250, run);
        assertEquals(0, pre);
        assertEquals(0, post);
        System.out.println(mean);
    }

    @Test
    public void testRepeat2() {
        final Timer timer = new Timer();
        final int zzz = 100;
        final double mean = timer.repeat(200, () -> zzz, t -> {
            GoToSleep(t, 0);
            return null;
        });
        assertEquals(200, new PrivateMethodTester(timer).invokePrivate("getLaps"));
        assertEquals(zzz, mean, 10);
        assertEquals(200, run);
        assertEquals(0, pre);
        assertEquals(0, post);
    }

    @Test // Slow
    public void testRepeat3() {
        final Timer timer = new Timer();
        final int zzz = 100;
        final double mean = timer.repeat(200, () -> zzz, t -> {
            GoToSleep(t, 0);
            return null;
        }, t -> {
            GoToSleep(t, -1);
            return t;
        }, t -> GoToSleep(10, 1));
        assertEquals(200, new PrivateMethodTester(timer).invokePrivate("getLaps"));
        assertEquals(zzz, mean, 10);
        assertEquals(200, run);
        assertEquals(0, pre);
        assertEquals(0, post);
    }

    int pre = 0;
    int run = 0;
    int post = 0;

    private void GoToSleep(long mSecs, int which) {
        try {
            Thread.sleep(mSecs);
            if (which == 0) run++;
            else if (which > 0) post++;
            else pre++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static final int TENTH = 100;
    public static final double TENTH_DOUBLE = 100;
    public static final int HUNDREDTH = 10;
    public static final int ONETH = 1000;

}