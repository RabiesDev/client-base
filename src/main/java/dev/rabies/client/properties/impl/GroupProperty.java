package dev.rabies.client.properties.impl;

import dev.rabies.client.properties.Property;
import lombok.Builder;

import java.util.List;

public class GroupProperty extends Property<List<Property<?>>> {
    @Builder
    public GroupProperty(String identifier, List<Property<?>> defaultValue) {
        super(identifier, defaultValue);
    }

    public <T extends Property<?>> T tree(T property) {
        value.getValue().add(property);
        return property;
    }
}
