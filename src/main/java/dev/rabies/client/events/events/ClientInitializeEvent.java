package dev.rabies.client.events.events;

import dev.rabies.client.events.Event;
import dev.rabies.client.events.Timings;

public class ClientInitializeEvent extends Event {
    public ClientInitializeEvent() {
        super(Timings.POST);
    }
}
