package dev.rabies.client.properties.impl;

import dev.rabies.client.properties.Property;
import dev.rabies.client.properties.range.NumberRange;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RangeProperty<T extends Comparable<T>> extends Property<T> {
    private final NumberRange<T> range;

    @Builder
    public RangeProperty(String identifier, T defaultValue, NumberRange<T> range) {
        super(identifier, defaultValue);
        this.range = range;
    }
}
