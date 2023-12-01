package dev.rabies.client.utils.registry;

import java.util.Collection;

public interface RegistryProvider<T> {
    boolean exists(Class<? extends T> clazz);

    T getValue(String identifier);

    @SuppressWarnings("unchecked")
    T getValue(Class<? extends T> clazz);

    Collection<T> values();
}
