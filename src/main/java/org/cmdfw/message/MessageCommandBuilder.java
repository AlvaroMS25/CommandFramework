package org.cmdfw.message;

import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public interface MessageCommandBuilder {
    MessageCommandBuilder setName(@NotNull String name);
    MessageCommandBuilder setDescription(@NotNull String description);

    MessageCommandBuilder setAliases(String... aliases);

    MessageCommandBuilder addSubcommands(MessageCommand... commands);
    MessageCommandBuilder addChecks(Function<MessageCommandContext, Boolean>... functions);
}
