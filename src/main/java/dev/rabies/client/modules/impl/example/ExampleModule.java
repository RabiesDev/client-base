package dev.rabies.client.modules.impl.example;

import dev.rabies.client.events.Priorities;
import dev.rabies.client.events.Timings;
import dev.rabies.client.events.events.ClientInitializeEvent;
import dev.rabies.client.events.interfaces.Handler;
import dev.rabies.client.events.interfaces.Linker;
import dev.rabies.client.modules.BranchModule;
import dev.rabies.client.modules.DevelopingState;
import dev.rabies.client.modules.GenericModule;
import dev.rabies.client.modules.ModuleCategory;
import dev.rabies.client.modules.impl.example.branches.ExampleBranch;
import dev.rabies.client.properties.binding.BindMode;
import dev.rabies.client.properties.binding.KeyBinding;
import dev.rabies.client.properties.choice.ArrayHelper;
import dev.rabies.client.properties.choice.NamedChoice;
import dev.rabies.client.properties.impl.ChoiceProperty;
import dev.rabies.client.properties.impl.GroupProperty;
import dev.rabies.client.properties.impl.NumberProperty;
import dev.rabies.client.properties.impl.RangeProperty;
import dev.rabies.client.properties.range.NumberRange;

import java.util.List;
import java.util.function.Consumer;

public class ExampleModule extends GenericModule {
    private final GroupProperty generalGroup = properties.tree(GroupProperty.builder()
            .identifier("example.general")
            .defaultValue(List.of())
            .build()
    );
    private final ChoiceProperty<SomeMode> someModeProperty = generalGroup.tree(ChoiceProperty.<SomeMode>builder()
            .identifier("example.some_mode")
            .choices(SomeMode.values())
            .defaultValue(0)
            .build()
    );
    private final ChoiceProperty<BranchModule<ExampleModule>> branchModuleProperty = generalGroup.tree(ChoiceProperty.<BranchModule<ExampleModule>>builder()
            .identifier("example.branch_module")
            .choices(ArrayHelper.of(
                    new ExampleBranch(this)
                    // ...
            ))
            .defaultValue(0)
            .build()
    );
    private final RangeProperty<Float> floatRangeProperty = generalGroup.tree(RangeProperty.<Float>builder()
            .identifier("example.float_range")
            .range(NumberRange.of(0.0f, 1.0f))
            .defaultValue(NumberRange.of(0.0f, 0.5f))
            .build()
    );
    private final NumberProperty<Float> floatProperty = generalGroup.tree(NumberProperty.<Float>builder()
            .identifier("example.float")
            .defaultValue(0.1f)
            .build()
    );

    public ExampleModule() {
        super("example", ModuleCategory.MISC, DevelopingState.STABLE, KeyBinding.create(BindMode.TOGGLE, -1));
        floatRangeProperty.addListener(newValue -> {
            // some process
        });
    }

    @Override
    public void onActivate() {
        super.onActivate();
        branchModuleProperty.onActivate();
        // some process
    }

    @Override
    public void onInactivate() {
        super.onInactivate();
        branchModuleProperty.onInactivate();
        // some process
    }

    @Linker(priority = Priorities.HIGH, filter = {Timings.PRE, Timings.POST})
    public final Handler<ClientInitializeEvent> clientInitializeEventHandler = event -> {
        SomeMode someMode = someModeProperty.getChoice();
        System.out.println(someMode.choiceName());
        System.out.println(someModeProperty.isSame(SomeMode.MODE_1));

        NumberRange<Float> floatRange = floatRangeProperty.getValue();
        System.out.println(floatRange.getMin());
        System.out.println(floatRange.getMax());

        float floatValue = floatProperty.getValue();
        System.out.println(floatValue);
    };

    public enum SomeMode implements NamedChoice, Consumer<ExampleModule> {
        MODE_1 {
            @Override
            public void accept(ExampleModule exampleModule) {
                System.out.println("Accepted 1");
            }

            @Override
            public String choiceName() {
                return "Mode 1";
            }
        },
        MODE_2 {
            @Override
            public void accept(ExampleModule exampleModule) {
                System.out.println("Accepted 2");
            }

            @Override
            public String choiceName() {
                return "Mode 2";
            }
        }
    }
}
