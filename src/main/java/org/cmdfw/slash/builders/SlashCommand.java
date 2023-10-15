package org.cmdfw.slash.builders;

import org.cmdfw.slash.SlashCommandContext;

public interface SlashCommand {
    void register(SlashCommandBuilder builder);
    void execute(SlashCommandContext context) throws Exception;
}
