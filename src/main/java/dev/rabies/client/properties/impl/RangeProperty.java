package dev.rabies.client.properties.impl;

import dev.rabies.client.properties.Property;
import dev.rabies.client.properties.range.NumberRange;
import lombok.Builder;
import lombok.Getter;

import java.util.function.Supplier;

@Getter
public class RangeProperty<T extends Comparable<T>> extends Property<NumberRange<T>> {
    private final NumberRange<T> range;

    @Builder
    protected RangeProperty(String identifier, NumberRange<T> defaultValue, NumberRange<T> range, Supplier<Boolean> dependency) {
        super(identifier, defaultValue, dependency);
        this.range = range;
    }
}
