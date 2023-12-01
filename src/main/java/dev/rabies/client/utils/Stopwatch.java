package dev.rabies.client.utils;

public class Stopwatch {
    private long lastTime = 0L;

    public boolean finished(float time, boolean restart) {
        boolean finished = elapsed() >= time;
        if (finished && restart) {
            restart();
        }
        return finished;
    }

    public long elapsed() {
        return System.currentTimeMillis() - lastTime;
    }

    public void restart() {
        lastTime = System.currentTimeMillis();
    }
}
