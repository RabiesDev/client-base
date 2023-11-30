package dev.rabies.client.modules.impl.example.branches;

import dev.rabies.client.events.Priorities;
import dev.rabies.client.events.Timings;
import dev.rabies.client.events.events.ClientPlayerMoveEvent;
import dev.rabies.client.events.interfaces.Handler;
import dev.rabies.client.events.interfaces.Linker;
import dev.rabies.client.modules.BranchModule;
import dev.rabies.client.modules.impl.example.ExampleModule;

public class ExampleBranch extends BranchModule<ExampleModule> {
    public ExampleBranch(ExampleModule parentModule) {
        super(parentModule, "Mode 3");
    }

    @Override
    public void onActivate() {
        super.onActivate();
        // some process
    }

    @Override
    public void onInactivate() {
        super.onInactivate();
        // some process
    }

    @Linker(priority = Priorities.MEDIUM, filter = {Timings.PRE, Timings.POST})
    public final Handler<ClientPlayerMoveEvent> clientPlayerMoveEventHandler = event -> {
        // some process
    };
}
