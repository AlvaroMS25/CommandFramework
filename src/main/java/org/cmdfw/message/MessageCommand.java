package org.cmdfw.message;

import org.jetbrains.annotations.NotNull;

public interface MessageCommand {
    void register(MessageCommandBuilder builder);
    void execute(MessageCommandContext context) throws Exception;

    default boolean before(MessageCommandContext context) { return true; }
    default void after(MessageCommandContext context) {}
}
