package org.cmdfw.slash;

import org.jetbrains.annotations.NotNull;

public interface SlashCommandBuilder {
    SlashCommandBuilder syncGlobally();
    SlashCommandBuilder syncOnGuilds(long... id);

    SlashCommandArgument addArgument();
}
