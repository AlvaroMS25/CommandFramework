package org.cmdfw.message;

import org.jetbrains.annotations.NotNull;

public abstract class MessageCommand {
    abstract MessageCommandBuilder register(MessageCommandBuilder builder);
    abstract void execute(MessageCommandContext context) throws Exception;

    boolean before(MessageCommandContext context) { return true; }
    void after(MessageCommandContext context) {}
}
