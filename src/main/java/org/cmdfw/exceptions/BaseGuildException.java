package org.cmdfw.exceptions;

import net.dv8tion.jda.api.entities.Guild;

public abstract class BaseGuildException extends Exception {
    private final Guild guild;

    public BaseGuildException(Guild guild) {
        this.guild = guild;
    }

    public BaseGuildException(String message, Guild guild) {
        super(message);
        this.guild = guild;
    }

    public Guild getGuild() {
        return guild;
    }
}
