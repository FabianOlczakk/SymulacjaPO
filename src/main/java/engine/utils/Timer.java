package engine.utils;

public class Timer {
    private double lastTime;

    public Timer() {
        this.lastTime = getTime();
    }

    public double getTime() {
        return System.nanoTime() / 1_000_000_000.0;
    }

    public float getElapsedTime() {
        double time = getTime();
        float elapsedTime = (float)(time - lastTime);
        lastTime = time;
        return elapsedTime;
    }

    public double getLastTime() {
        return lastTime;
    }
}

