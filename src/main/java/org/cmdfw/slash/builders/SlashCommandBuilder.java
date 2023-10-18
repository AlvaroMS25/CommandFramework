package org.cmdfw.slash.builders;

public interface SlashCommandBuilder extends ExtendedPropertiesSetter<SlashCommandBuilder> {
    SlashCommandArgument addArgument();
}
