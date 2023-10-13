package org.cmdfw.slash;

import org.jetbrains.annotations.NotNull;

public interface SlashCommandBuilder {
    SlashCommandBuilder syncGlobally();
    SlashCommandBuilder syncOnGuilds(long... id);
    SlashCommandBuilder setName(@NotNull String name);
    SlashCommandBuilder setDescription(@NotNull String description);

    SlashCommandArgument addArgument();
}
