package dev.rabies.client.properties;

import dev.rabies.client.modules.GenericModule;
import lombok.Getter;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Getter
public class Properties {
    private final List<Property<?>> properties = new CopyOnWriteArrayList<>();
    private final GenericModule parentModule;

    protected Properties(GenericModule parentModule) {
        this.parentModule = parentModule;
    }

    public static Properties create(GenericModule parentModule) {
        return new Properties(parentModule);
    }

    public <T extends Property<?>> T tree(T property) {
        property.setParentModule(parentModule);
        properties.add(property);
        return property;
    }
}
