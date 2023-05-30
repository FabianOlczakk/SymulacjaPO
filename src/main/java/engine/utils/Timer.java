package engine.utils;

public class Timer {
    private double lastTime;

    /**
     * Creates a Timer object and initializes the last recorded time.
     */
    public Timer() {
        this.lastTime = getTime();
    }

    /**
     * Returns the current time in seconds.
     *
     * @return The current time in seconds.
     */
    public double getTime() {
        return System.nanoTime() / 1_000_000_000.0;
    }

    /**
     * Calculates and returns the elapsed time since the last recorded time.
     * Updates the last recorded time to the current time.
     *
     * @return The elapsed time in seconds.
     */
    public float getElapsedTime() {
        double time = getTime();
        float elapsedTime = (float) (time - lastTime);
        lastTime = time;
        return elapsedTime;
    }

    /**
     * Returns the last recorded time.
     *
     * @return The last recorded time in seconds.
     */
    public double getLastTime() {
        return lastTime;
    }
}


