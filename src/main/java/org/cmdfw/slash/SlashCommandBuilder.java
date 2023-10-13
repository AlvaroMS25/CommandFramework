package org.cmdfw.slash;

import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import org.jetbrains.annotations.NotNull;

public interface SlashCommandBuilder {
    SlashCommandBuilder syncGlobally();
    SlashCommandBuilder syncOnGuilds(long... id);
    SlashCommandBuilder setName(@NotNull String name);
    SlashCommandBuilder setDescription(@NotNull String description);
    SlashCommandBuilder setDefaultPermissions(@NotNull DefaultMemberPermissions permission);

    SlashCommandArgument addArgument();
}
