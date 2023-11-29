package dev.rabies.client.properties;

import lombok.Getter;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Getter
public class Properties {
    private final List<Property<?>> properties = new CopyOnWriteArrayList<>();

    public <T extends Property<?>> T tree(T property) {
        properties.add(property);
        return property;
    }
}
