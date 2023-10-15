package org.cmdfw.slash;

public interface SlashCommandManager {
    void registerAll();
    void registerAtGuild(long guildId);
}
