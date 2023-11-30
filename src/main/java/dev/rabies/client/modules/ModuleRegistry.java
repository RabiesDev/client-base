package dev.rabies.client.modules;

import dev.rabies.client.modules.impl.example.ExampleModule;
import dev.rabies.client.modules.impl.sprint.SprintModule;
import dev.rabies.client.utils.InstanceRegistry;

public class ModuleRegistry extends InstanceRegistry<GenericModule> {
    public ModuleRegistry() {
        super(
                new ExampleModule(),
                new SprintModule()
        );
    }
}
