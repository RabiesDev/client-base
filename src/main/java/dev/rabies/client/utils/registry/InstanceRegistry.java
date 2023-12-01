package dev.rabies.client.utils.registry;

import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.MutableClassToInstanceMap;

import java.util.Collection;

public abstract class InstanceRegistry<T> implements RegistryProvider<T> {
    protected final ClassToInstanceMap<T> instanceMap;

    @SafeVarargs
    @SuppressWarnings("unchecked")
    public InstanceRegistry(T... values) {
        instanceMap = MutableClassToInstanceMap.create();
        for (T value : values) {
            instanceMap.putInstance((Class<T>) value.getClass(), value);
        }
    }

    @SuppressWarnings("unchecked")
    public T addInstance(T value) {
        instanceMap.putInstance((Class<T>) value.getClass(), value);
        return value;
    }

    @SuppressWarnings("unchecked")
    public T removeInstance(T value) {
        instanceMap.remove((Class<T>) value.getClass());
        return value;
    }

    @Override
    public boolean exists(Class<? extends T> clazz) {
        return getValue(clazz) != null;
    }

    @Override
    public T getValue(Class<? extends T> clazz) {
        return instanceMap.get(clazz);
    }

    @Override
    public Collection<T> values() {
        return instanceMap.values();
    }
}
