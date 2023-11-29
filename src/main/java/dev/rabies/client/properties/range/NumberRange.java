package dev.rabies.client.properties.range;

public class NumberRange<T extends Comparable<T>> {
    private T min, max;

    protected NumberRange(T min, T max) {
        this.min = min;
        this.max = max;
    }

    public static <T extends Comparable<T>> NumberRange<T> of(T min, T max) {
        return new NumberRange<>(min, max);
    }

    public boolean contains(T value) {
        return value.compareTo(min) >= 0 && value.compareTo(max) <= 0;
    }

    public void setMin(T min) {
        this.min = min;
    }

    public void setMax(T max) {
        this.max = max;
    }
}
