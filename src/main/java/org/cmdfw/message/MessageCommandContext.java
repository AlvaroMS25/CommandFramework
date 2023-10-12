package org.cmdfw.message;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public interface MessageCommandContext {
    public List<String> getArgs();
    public JDA getJda();

    public MessageReceivedEvent getEvent();
}
