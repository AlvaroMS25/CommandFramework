package org.cmdfw.slash.builders;

import org.cmdfw.slash.SlashCommandContext;

public interface SlashCommand {
    void register(SlashCommandBuilder builder);
    default void execute(SlashCommandContext context) throws Exception {}
}
