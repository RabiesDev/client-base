package dev.rabies.client.properties.impl;

import dev.rabies.client.properties.Property;
import lombok.Builder;

import java.util.List;
import java.util.function.Supplier;

public class GroupProperty extends Property<List<Property<?>>> {
    @Builder
    protected GroupProperty(String identifier, List<Property<?>> defaultValue, Supplier<Boolean> dependency) {
        super(identifier, defaultValue, dependency);
    }

    public <T extends Property<?>> T tree(T property) {
        property.setParentProperty(this);
        value.getValue().add(property);
        return property;
    }

    public int treeSize() {
        return getValue().size();
    }
}
