package dev.rabies.client.modules;

import dev.rabies.client.properties.MappedValue;
import dev.rabies.client.properties.Properties;
import dev.rabies.client.properties.binding.KeyBinding;
import dev.rabies.client.utils.Toggleable;
import lombok.Getter;

public class GenericModule implements Toggleable {
    private String internalIdentifier = "";
    private ModuleCategory internalCategory = ModuleCategory.MISC;
    private DevelopingState internalDevelopingState = DevelopingState.STABLE;

    private KeyBinding internalKeyBinding = KeyBinding.none();
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
    protected final MappedValue<DevelopingState> developingState = MappedValue.<DevelopingState>create()
            .setUpdater(state -> internalDevelopingState = state)
            .setResolver(() -> internalDevelopingState)
            .build();
    @Getter
    protected final MappedValue<KeyBinding> keybinding = MappedValue.<KeyBinding>create()
            .setUpdater(keyBinding -> internalKeyBinding = keyBinding)
            .setResolver(() -> internalKeyBinding)
            .build();
    @Getter
    protected final MappedValue<Boolean> activated = MappedValue.<Boolean>create()
            .setUpdater(state -> {
                internalState = state;
                if (state) {
                    onActivate();
                } else {
                    onInactivate();
                }
            })
            .setResolver(() -> internalState)
            .build();
    @Getter
    protected final Properties properties = Properties.create(this);

    public GenericModule(String identifier, ModuleCategory category) {
        this.identifier.setValue(identifier);
        this.category.setValue(category);
    }

    public GenericModule(String identifier, ModuleCategory category, DevelopingState developingState) {
        this.identifier.setValue(identifier);
        this.category.setValue(category);
        this.developingState.setValue(developingState);
    }

    public GenericModule(String identifier, ModuleCategory category, KeyBinding keybinding) {
        this.identifier.setValue(identifier);
        this.category.setValue(category);
        this.keybinding.setValue(keybinding);
    }

    public GenericModule(String identifier, ModuleCategory category, DevelopingState developingState, KeyBinding keybinding) {
        this.identifier.setValue(identifier);
        this.category.setValue(category);
        this.developingState.setValue(developingState);
        this.keybinding.setValue(keybinding);
    }

    @Override
    public void onActivate() {
    }

    @Override
    public void onInactivate() {
    }
}
