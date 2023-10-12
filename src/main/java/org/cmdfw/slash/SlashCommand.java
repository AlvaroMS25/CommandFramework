package org.cmdfw.slash;

public abstract class SlashCommand {
    abstract SlashCommandBuilder register(SlashCommandBuilder builder);
    abstract void execute(SlashCommandContext context) throws Exception;
}
