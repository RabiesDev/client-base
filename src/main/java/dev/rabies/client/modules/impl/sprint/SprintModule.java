package dev.rabies.client.modules.impl.sprint;

import dev.rabies.client.events.Priorities;
import dev.rabies.client.events.events.ClientPlayerMoveEvent;
import dev.rabies.client.events.interfaces.Handler;
import dev.rabies.client.events.interfaces.Linker;
import dev.rabies.client.modules.GenericModule;
import dev.rabies.client.modules.ModuleCategory;
import dev.rabies.client.properties.impl.BooleanProperty;

public class SprintModule extends GenericModule {
    private final BooleanProperty strictProperty = properties.tree(BooleanProperty.builder()
            .identifier("sprint.strict")
            .defaultValue(true)
            .build()
    );

    public SprintModule() {
        super("sprint", ModuleCategory.MOVEMENT);
    }

    @Linker(priority = Priorities.LOW)
    public final Handler<ClientPlayerMoveEvent> clientPlayerMoveEventHandler = event -> {
        // mc.player.setSprinting(true);
    };
}
