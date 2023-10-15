package org.cmdfw.slash.builders;

public interface SlashCommandBuilder {
    SimpleCommandBuilder simple();
    SimpleGroupBuilder addSubcommands();
    SubcommandGroupBuilder addSubcommandGroup();
}
