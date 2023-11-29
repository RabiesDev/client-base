package dev.rabies.client.modules;

import dev.rabies.client.properties.choice.NamedChoice;
import dev.rabies.client.utils.Toggleable;

public class BranchModule<T extends GenericModule> implements Toggleable, NamedChoice {
    protected final T parentModule;
    protected final String choiceName;

    public BranchModule(T parentModule, String choiceName) {
        this.parentModule = parentModule;
        this.choiceName = choiceName;
    }

    @Override
    public void onActivate() {
    }

    @Override
    public void onInactivate() {
    }

    @Override
    public String choiceName() {
        return choiceName;
    }
}
