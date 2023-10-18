package org.cmdfw.slash;

import org.cmdfw.slash.builders.SlashCommand;
import org.cmdfw.slash.builders.SubCommandGroup;

public interface SlashCommandManager {
    void registerAll();
    void registerAtGuild(long guildId);

    void register(SlashCommand command);
    void register(SubCommandGroup group);
}
