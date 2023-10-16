package org.cmdfw.slash.builders;

import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import org.cmdfw.slash.BuildableContainer;
import org.jetbrains.annotations.NotNull;

public interface SimpleCommandBuilder extends ExtendedPropertiesSetter<SimpleCommandBuilder> {
    SlashCommandArgument addArgument();
}
