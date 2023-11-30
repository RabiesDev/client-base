package dev.rabies.client.events.events;

import dev.rabies.client.events.Event;
import dev.rabies.client.events.Timings;

public class ClientPlayerMoveEvent extends Event {
    public ClientPlayerMoveEvent(Timings timing) {
        super(timing);
    }
}
