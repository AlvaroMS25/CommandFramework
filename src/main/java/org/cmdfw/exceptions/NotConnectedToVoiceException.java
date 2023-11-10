package org.cmdfw.exceptions;

import net.dv8tion.jda.api.entities.Guild;

public class NotConnectedToVoiceException extends BaseGuildException {

    public NotConnectedToVoiceException(Guild guild) {
        super(String.format("Not connected to voice at %s", guild.getName()), guild);
    }

}
