package dev.rabies.client.modules;

import dev.rabies.client.modules.impl.example.ExampleModule;
import dev.rabies.client.modules.impl.sprint.SprintModule;
import dev.rabies.client.utils.registry.InstanceRegistry;

public class ModuleRegistry extends InstanceRegistry<GenericModule> {
    public ModuleRegistry() {
        super(
                new ExampleModule(),
                new SprintModule()
        );
    }

    @Override
    public GenericModule getValue(String identifier) {
        for (GenericModule module : values()) {
            if (module.getIdentifier().getValue().equals(identifier)) {
                return module;
            }
        }
        return null;
    }
}
