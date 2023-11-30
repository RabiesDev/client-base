package dev.rabies.client.properties.impl;

import dev.rabies.client.properties.Property;
import lombok.Builder;

import java.util.function.Supplier;

public class BooleanProperty extends Property<Boolean> {
    @Builder
    protected BooleanProperty(String identifier, Boolean defaultValue, Supplier<Boolean> dependency) {
        super(identifier, defaultValue, dependency);
    }
}
