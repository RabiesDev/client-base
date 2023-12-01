package dev.rabies.client.utils.math;

public class MathHelper {
    public static double sigmoid(double x) {
        return 1 / (1 + Math.exp(-x));
    }

    public static double fade(double t) {
        return t * t * t * (t * (t * 6 - 15) + 10);
    }

    public static double lerp(double t, double a, double b) {
        return a + t * (b - a);
    }

    public static double grad(int hash, double x, double y, double z) {
        int h = hash & 15;
        double u = h < 8 ? x : y;
        double v = h < 4 ? y : h == 12 || h == 14 ? x : z;
        return ((h & 1) == 0 ? u : -u) + ((h & 2) == 0 ? v : -v);
    }
}
