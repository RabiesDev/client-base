package dev.rabies.client.properties.impl;

import dev.rabies.client.properties.Property;
import dev.rabies.client.properties.range.NumberRange;
import lombok.Builder;
import lombok.Getter;

import java.util.function.Supplier;

@Getter
public class NumberProperty<T extends Comparable<T>> extends Property<T> {
    private final NumberRange<T> range;
    private final T interval;

    @Builder
    protected NumberProperty(String identifier, T defaultValue, T interval, NumberRange<T> range, Supplier<Boolean> dependency) {
        super(identifier, defaultValue, dependency);
        this.range = range;
        this.interval = interval;
    }
}
