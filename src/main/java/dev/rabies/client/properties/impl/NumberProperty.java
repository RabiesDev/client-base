package dev.rabies.client.properties.impl;

import dev.rabies.client.properties.Property;
import lombok.Builder;
import lombok.Getter;

import java.util.function.Supplier;

@Getter
public class NumberProperty<T extends Number> extends Property<T> {
    private final T interval;

    @Builder
    protected NumberProperty(String identifier, T defaultValue, T interval, Supplier<Boolean> dependency) {
        super(identifier, defaultValue, dependency);
        this.interval = interval;
    }
}
