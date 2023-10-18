package org.cmdfw.slash;

import org.cmdfw.slash.builders.SlashCommand;
import org.cmdfw.slash.builders.SubCommandGroup;

public interface SlashCommandManager {
    void registerAll();
    void registerAtGuild(long guildId);

    SlashCommandManager register(SlashCommand command);
    SlashCommandManager register(SubCommandGroup group);
}
