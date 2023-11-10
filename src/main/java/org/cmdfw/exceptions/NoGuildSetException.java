package org.cmdfw.exceptions;

public class NoGuildSetException extends Exception {
    public NoGuildSetException(Long guildId) {
        super(String.format("No guild object set for guild: %s", guildId.toString()));
    }
}
