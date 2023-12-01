package dev.rabies.client.commands.impl;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import dev.rabies.client.commands.Command;
import dev.rabies.client.commands.arguments.ModuleArgumentType;
import dev.rabies.client.modules.GenericModule;
import net.minecraft.command.ISuggestionProvider;

public class ExampleCommand extends Command<ISuggestionProvider> {
    public ExampleCommand() {
        super("example");
    }

    @Override
    public void build(LiteralArgumentBuilder<ISuggestionProvider> builder) {
        builder.then(argument("module", new ModuleArgumentType()).executes(context -> {
            GenericModule module = context.getArgument("module", GenericModule.class);
            System.out.println(module.getIdentifier().getValue());
            return SINGLE_SUCCESS;
        }));
    }
}
