package dev.rabies.client.utils;

import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.MutableClassToInstanceMap;

import java.util.Arrays;
import java.util.Collection;

public class InstanceRegistry<T> {
    protected final ClassToInstanceMap<T> instanceMap;

    @SafeVarargs
    @SuppressWarnings("unchecked")
    public InstanceRegistry(T... values) {
        instanceMap = MutableClassToInstanceMap.create();
        Arrays.asList(values).forEach(value -> instanceMap.putInstance((Class<T>) value.getClass(), value));
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

    public boolean exists(Class<? extends T> clazz) {
        return valueByClass(clazz) != null;
    }

    @SuppressWarnings("unchecked")
    public <Any extends T> Any valueByClass(Class<? extends T> clazz) {
        return (Any) instanceMap.get(clazz);
    }

    public Collection<T> values() {
        return instanceMap.values();
    }
}
