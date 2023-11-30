package dev.rabies.client.properties;

import dev.rabies.client.modules.GenericModule;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Property<T> {
    protected T internalValue;
    @Getter @Setter
    protected GenericModule parentModule;
    @Getter @Setter
    protected Property<?> parentProperty;
    @Getter @Setter
    protected Supplier<Boolean> dependency;

    protected final List<Consumer<T>> listeners;
    protected final MappedValue<T> value;
    @Getter
    protected final String identifier;

    protected Property(String identifier, T defaultValue, Supplier<Boolean> dependency) {
        this.identifier = identifier;
        this.internalValue = defaultValue;
        this.dependency = dependency;

        this.listeners = new CopyOnWriteArrayList<>();
        this.value = MappedValue.<T>create()
                .setUpdater(value -> {
                    listeners.forEach(listener -> listener.accept(value));
                    internalValue = value;
                })
                .setResolver(() -> internalValue)
                .build();
    }

    public void addListener(Consumer<T> listener) {
        listeners.add(listener);
    }

    public void setValue(T value) {
        this.value.setValue(value);
    }

    public T getValue() {
        return value.getValue();
    }
}
