package org.cmdfw.slash.builders;

import org.cmdfw.slash.SlashCommandContext;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public interface DeferredSlashCommandBuilder extends SlashCommandBuilder {
    void setHandler(@NotNull Consumer<SlashCommandContext> handler);
}
