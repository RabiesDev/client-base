package dev.rabies.client.properties;

import java.util.function.Consumer;
import java.util.function.Supplier;

// reference: https://github.com/u64lisa/client-base
public class MappedValue<V> {
    private Consumer<V> updater;
    private Supplier<V> resolver;
    private V defaultValue;

    public static <V> MappedValue<V> create() {
        return new MappedValue<>();
    }

    public MappedValue<V> setUpdater(Consumer<V> updater) {
        this.updater = updater;
        return this;
    }

    public MappedValue<V> setResolver(Supplier<V> resolver) {
        this.resolver = resolver;
        return this;
    }

    public MappedValue<V> build() {
        if (resolver != null) {
            defaultValue = resolver.get();
        }

        if (updater == null || resolver == null) {
            throw new IllegalStateException("Missing Resolve method or Update method");
        }
        return this;
    }

    public void setValue(V value) {
        if (resolver != null) {
            defaultValue = resolver.get();
        }
        updater.accept(value);
    }

    public V getValue() {
        return resolver.get();
    }
}
