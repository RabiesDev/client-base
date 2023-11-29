package dev.rabies.client.modules.impl;

import dev.rabies.client.modules.GenericModule;
import dev.rabies.client.modules.ModuleCategory;
import dev.rabies.client.properties.binding.Keybinding;
import dev.rabies.client.properties.impl.GroupProperty;
import dev.rabies.client.properties.impl.RangeProperty;
import dev.rabies.client.properties.range.NumberRange;

import java.util.List;

public class ExampleModule extends GenericModule {
    private final GroupProperty generalGroup = properties.tree(GroupProperty.builder()
            .identifier("example.general")
            .defaultValue(List.of())
            .build()
    );
    private final RangeProperty<Float> floatRangeProperty = generalGroup.tree(RangeProperty.<Float>builder()
            .identifier("example.float_range")
            .range(NumberRange.of(0.0f, 1.0f))
            .defaultValue(0.1f)
            .build()
    );

    public ExampleModule() {
        super("example", ModuleCategory.MISC, Keybinding.none());
    }
}
