package dev.rabies.client;

import dev.rabies.client.commands.CommandRegistry;
import dev.rabies.client.modules.ModuleRegistry;
import lombok.Getter;
import lombok.Setter;

public class BaseClient {
    @Getter @Setter
    private static ModuleRegistry moduleRegistry;
    @Getter @Setter
    private static CommandRegistry commandRegistry;
}
