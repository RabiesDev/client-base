package dev.rabies.client.utils.math;

import java.util.Random;

public class SmoothRandom {
    private static final int PERM_SIZE = 512;
    private final int[] permutation;

    public SmoothRandom(long seed) {
        Random random = new Random(seed);
        permutation = new int[PERM_SIZE * 2];

        for (int i = 0; i < PERM_SIZE; i++) {
            permutation[i] = i;
        }

        for (int i = 0; i < PERM_SIZE; i++) {
            int j = random.nextInt(PERM_SIZE);
            int temp = permutation[i];
            permutation[i] = permutation[j];
            permutation[j] = temp;
            permutation[i + PERM_SIZE] = permutation[i];
        }
    }

    public double noise(double x, double y, double z) {
        int X = (int) Math.floor(x) & 255;
        int Y = (int) Math.floor(y) & 255;
        int Z = (int) Math.floor(z) & 255;

        x -= Math.floor(x);
        y -= Math.floor(y);
        z -= Math.floor(z);

        double u = MathHelper.fade(x);
        double v = MathHelper.fade(y);
        double w = MathHelper.fade(z);

        int A = permutation[X] + Y;
        int AA = permutation[A] + Z;
        int AB = permutation[A + 1] + Z;
        int B = permutation[X + 1] + Y;
        int BA = permutation[B] + Z;
        int BB = permutation[B + 1] + Z;

        double l1 = MathHelper.lerp(u, MathHelper.grad(permutation[AA], x, y, z), MathHelper.grad(permutation[BA], x - 1, y, z));
        double l2 = MathHelper.lerp(u, MathHelper.grad(permutation[AB], x, y - 1, z), MathHelper.grad(permutation[BB], x - 1, y - 1, z));
        double l3 = MathHelper.lerp(u, MathHelper.grad(permutation[AA + 1], x, y, z - 1), MathHelper.grad(permutation[BA + 1], x - 1, y, z - 1));
        double l4 = MathHelper.lerp(u, MathHelper.grad(permutation[AB + 1], x, y - 1, z - 1), MathHelper.grad(permutation[BB + 1], x - 1, y - 1, z - 1));
        return (MathHelper.lerp(w, MathHelper.lerp(v, l1, l2), MathHelper.lerp(v, l3, l4)) + 1) / 2;
    }
}