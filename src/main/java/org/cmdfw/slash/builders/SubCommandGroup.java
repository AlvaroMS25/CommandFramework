package org.cmdfw.slash.builders;

import org.cmdfw.slash.SlashCommandContext;

public interface SubCommandGroup extends ExtendedPropertiesSetter<SubCommandGroup> {
    default void register(SimpleGroupBuilder builder) {}
    default void register(SubcommandGroupBuilder builder) {}
}
