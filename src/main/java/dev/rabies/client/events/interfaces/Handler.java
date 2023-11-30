package dev.rabies.client.events.interfaces;

@FunctionalInterface
public interface Handler<T> {
    void invoke(T event);
}
