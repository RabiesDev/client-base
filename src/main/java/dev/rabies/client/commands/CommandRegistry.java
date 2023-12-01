package dev.rabies.client.commands;

import com.mojang.brigadier.CommandDispatcher;
import dev.rabies.client.commands.impl.ExampleCommand;
import dev.rabies.client.utils.registry.InstanceRegistry;
import net.minecraft.command.ISuggestionProvider;

import java.util.Arrays;

// reference: https://github.com/MeteorDevelopment/meteor-client
public class CommandRegistry extends InstanceRegistry<Command<ISuggestionProvider>> {
    private final CommandDispatcher<ISuggestionProvider> dispatcher = new CommandDispatcher<>();
    private final String[] prefixes = {
            ".", "-"
    };

    public CommandRegistry() {
        super(
                new ExampleCommand()
        );

        for (Command<ISuggestionProvider> command : values()) {
            command.registerTo(dispatcher);
        }
    }

    @Override
    public Command<ISuggestionProvider> getValue(String identifier) {
        for (Command<ISuggestionProvider> command : values()) {
            if (Arrays.asList(command.getAliases()).contains(identifier)) {
                return command;
            }
        }
        return null;
    }
}
