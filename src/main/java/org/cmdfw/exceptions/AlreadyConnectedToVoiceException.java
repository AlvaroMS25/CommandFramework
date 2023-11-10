package org.cmdfw.exceptions;

import net.dv8tion.jda.api.entities.Guild;

public class AlreadyConnectedToVoiceException extends BaseGuildException {
    public AlreadyConnectedToVoiceException(Guild guild) {
        super(String.format("Already connected to voice at guild %s", guild.getName()), guild);
    }
}
