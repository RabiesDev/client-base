package dev.rabies.client.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import lombok.Getter;

@Getter
public abstract class Command<S> {
    protected final int SINGLE_SUCCESS = 1;
    private final String[] aliases;

    public Command(String... aliases) {
        this.aliases = aliases;
    }

    public abstract void build(LiteralArgumentBuilder<S> builder);

    public void registerTo(CommandDispatcher<S> dispatcher) {
        for (String alias : aliases) {
            LiteralArgumentBuilder<S> builder = LiteralArgumentBuilder.literal(alias);
            build(builder);
            dispatcher.register(builder);
        }
    }

    protected <T> RequiredArgumentBuilder<S, T> argument(String name, ArgumentType<T> type) {
        return RequiredArgumentBuilder.argument(name, type);
    }

    protected LiteralArgumentBuilder<S> literal(String name) {
        return LiteralArgumentBuilder.literal(name);
    }
}
