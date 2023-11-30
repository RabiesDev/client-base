package dev.rabies.client.properties.choice;

public class ArrayHelper {
    @SafeVarargs
    public static <T> T[] of(T... ts) {
        return ts;
    }
}
