package dev.rabies.client.properties.impl;

import dev.rabies.client.events.Subscriber;
import dev.rabies.client.modules.BranchModule;
import dev.rabies.client.properties.Property;
import dev.rabies.client.properties.choice.NamedChoice;
import dev.rabies.client.utils.Toggleable;
import lombok.Builder;
import lombok.Getter;

import java.util.function.Supplier;

@Getter
public class ChoiceProperty<T extends NamedChoice> extends Property<Integer> implements Toggleable {
    private final T[] choices;

    @Builder
    protected ChoiceProperty(String identifier, Integer defaultValue, T[] choices, Supplier<Boolean> dependency) {
        super(identifier, defaultValue, dependency);
        this.choices = choices;
    }

    @Override
    public void onActivate() {
        if (getChoice() instanceof BranchModule<?> currentChoice) {
            currentChoice.onActivate();
            Subscriber.register(currentChoice);
        }
    }

    @Override
    public void onInactivate() {
        if (getChoice() instanceof BranchModule<?> currentChoice) {
            Subscriber.unregister(currentChoice);
            currentChoice.onInactivate();
        }
    }

    public boolean isSame(int index) {
        return getValue() == index;
    }

    public boolean isSame(T choice) {
        return getChoice().equals(choice);
    }

    public boolean isSame(String name) {
        return getChoice().choiceName().equals(name);
    }

    public T getChoice() {
        return choices[getValue()];
    }
}
