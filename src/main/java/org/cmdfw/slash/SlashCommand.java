package org.cmdfw.slash;

public interface SlashCommand {
    SlashCommandBuilder register(SlashCommandBuilder builder);
    void execute(SlashCommandContext context) throws Exception;
}
