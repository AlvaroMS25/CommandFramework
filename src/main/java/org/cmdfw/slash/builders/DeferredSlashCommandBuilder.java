package org.cmdfw.slash.builders;

public interface DeferredSlashCommandBuilder extends BasePropertiesSetter<DeferredSlashCommandBuilder> {
    SlashCommandArgument addArgument();
}
