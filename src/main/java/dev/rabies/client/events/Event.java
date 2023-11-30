package dev.rabies.client.events;

public class Event {
    public final Timings timing;
    public boolean cancelled;

    public Event(Timings timing) {
        this.timing = timing;
    }
}
