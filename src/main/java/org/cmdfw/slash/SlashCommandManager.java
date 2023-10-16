package org.cmdfw.slash;

import org.cmdfw.slash.builders.SlashCommand;

public interface SlashCommandManager {
    void registerAll();
    void registerAtGuild(long guildId);

    void register(SlashCommand command);
}
