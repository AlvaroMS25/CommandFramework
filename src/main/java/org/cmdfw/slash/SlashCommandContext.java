package org.cmdfw.slash;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;

public interface SlashCommandContext {
    JDA getJda();
    SlashCommandInteractionEvent getEvent();

    default MessageChannelUnion getChannel() {
        return getEvent().getChannel();
    }

    default SlashCommandInteraction getInteraction() {
        return getEvent().getInteraction();
    }
}
