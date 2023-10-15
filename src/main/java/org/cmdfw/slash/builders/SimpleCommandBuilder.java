package org.cmdfw.slash.builders;

import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import org.cmdfw.slash.BuildableContainer;
import org.jetbrains.annotations.NotNull;

public interface SimpleCommandBuilder extends BuildableContainer, BasePropertiesSetter<SimpleCommandBuilder> {
    SimpleCommandBuilder setDefaultPermissions(@NotNull DefaultMemberPermissions permission);
    SimpleCommandBuilder setGuildOnly(boolean isGuildOnly);
    SimpleCommandBuilder setNsfw(boolean isNsfw);

    SlashCommandArgument addArgument();
}
