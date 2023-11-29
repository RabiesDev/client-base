package dev.rabies.client.properties;

import lombok.Getter;

public class Property<T> {
    private T internalValue;
    protected final MappedValue<T> value;
    @Getter
    protected final String identifier;

    public Property(String identifier, T defaultValue) {
        this.identifier = identifier;
        this.internalValue = defaultValue;
        this.value = MappedValue.<T>create()
                .setUpdater(value -> internalValue = value)
                .setResolver(() -> internalValue)
                .build();
    }

    public void setValue(T value) {
        this.value.setValue(value);
    }

    public T getValue() {
        return value.getValue();
    }
}
