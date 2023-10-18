package org.cmdfw.slash.builders;

import org.cmdfw.slash.BuildableContainer;

public interface SimpleGroupBuilder extends ExtendedPropertiesSetter<SimpleGroupBuilder> {
    DeferredSlashCommandBuilder addCommand();
    SimpleGroupBuilder addCommand(SlashCommand command);
}
