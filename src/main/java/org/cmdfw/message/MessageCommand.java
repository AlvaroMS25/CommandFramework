package org.cmdfw.message;

import org.jetbrains.annotations.NotNull;

public interface MessageCommand {
    abstract MessageCommandBuilder register(MessageCommandBuilder builder);
    abstract void execute(MessageCommandContext context) throws Exception;

    default boolean before(MessageCommandContext context) { return true; }
    default void after(MessageCommandContext context) {}
}
