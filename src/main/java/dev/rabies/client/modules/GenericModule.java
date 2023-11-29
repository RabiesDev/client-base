package dev.rabies.client.modules;

import dev.rabies.client.properties.MappedValue;
import dev.rabies.client.properties.Properties;
import dev.rabies.client.properties.binding.Keybinding;
import dev.rabies.client.utils.Toggleable;
import lombok.Getter;

public class GenericModule implements Toggleable {
    private String internalIdentifier = "";
    private ModuleCategory internalCategory = ModuleCategory.MISC;
    private boolean internalState = false;

    @Getter
    protected final MappedValue<String> identifier = MappedValue.<String>create()
            .setUpdater(identifier -> internalIdentifier = identifier)
            .setResolver(() -> internalIdentifier)
            .build();
    @Getter
    protected final MappedValue<ModuleCategory> category = MappedValue.<ModuleCategory>create()
            .setUpdater(category -> internalCategory = category)
            .setResolver(() -> internalCategory)
            .build();
    @Getter
    protected final MappedValue<Boolean> activated = MappedValue.<Boolean>create()
            .setUpdater(state -> internalState = state)
            .setResolver(() -> internalState)
            .build();
    @Getter
    protected final Properties properties = new Properties();
    @Getter
    protected final Keybinding keybinding;

    public GenericModule(String identifier, ModuleCategory category, Keybinding keybinding) {
        this.identifier.setValue(identifier);
        this.category.setValue(category);
        this.keybinding = keybinding;
    }

    @Override
    public void onActivate() {
    }

    @Override
    public void onInactivate() {
    }

    public void switchState() {
        if (activated.getValue()) {
            onActivate();
            return;
        }

        onInactivate();
    }
}
