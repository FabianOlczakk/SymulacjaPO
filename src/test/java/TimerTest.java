import engine.utils.Timer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TimerTest {

    @Test
    public void testGetTime() {
        Timer timer = new Timer();

        double currentTime = timer.getTime();

        assertTrue(currentTime > 0.0);
    }

    @Test
    public void testGetElapsedTime() {
        Timer timer = new Timer();

        // First call to getElapsedTime should return a positive value
        float elapsedTime1 = timer.getElapsedTime();
        assertTrue(elapsedTime1 > 0.0f);

        // Second call to getElapsedTime should return a positive value
        float elapsedTime2 = timer.getElapsedTime();
        assertTrue(elapsedTime2 > 0.0f);

        // Elapsed time should increase between consecutive calls
        assertTrue(elapsedTime2 > elapsedTime1);
    }

    @Test
    public void testGetLastTime() {
        Timer timer = new Timer();

        double lastTime1 = timer.getLastTime();

        // Call to getElapsedTime should update the last recorded time
        float elapsedTime = timer.getElapsedTime();

        double lastTime2 = timer.getLastTime();

        assertTrue(lastTime2 > lastTime1);
    }
}