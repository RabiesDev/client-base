package dev.rabies.client.utils.math;

public class RandomHelper {
    public static int randomInteger(int min, int max) {
        return (int) randomDouble(min, max);
    }

    public static float randomFloat(float min, float max) {
        return (float) randomDouble(min, max);
    }

    public static double randomDouble(double min, double max) {
        return Math.random() * (max - min) + min;
    }
}
