package dev.rabies.client.commands.arguments;

import com.mojang.brigadier.LiteralMessage;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import dev.rabies.client.BaseClient;
import dev.rabies.client.modules.GenericModule;

import java.util.concurrent.CompletableFuture;

public class ModuleArgumentType implements ArgumentType<GenericModule> {
    private final SimpleCommandExceptionType MODULE_NOT_FOUND = new SimpleCommandExceptionType(new LiteralMessage("\247cNo module found"));

    @Override
    public <S> GenericModule parse(StringReader reader) throws CommandSyntaxException {
        String argument = reader.readString();
        GenericModule module = BaseClient.getModuleRegistry().getValue(argument);
        if (module == null) {
            throw MODULE_NOT_FOUND.create();
        }
        return module;
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        for (GenericModule module : BaseClient.getModuleRegistry().values()) {
            builder.suggest(module.getIdentifier().getValue().replaceAll(" ", ""));
        }
        return builder.buildFuture();
    }
}
