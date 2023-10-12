package org.cmdfw.slash;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;

public interface AutocompleteContext {
    CommandAutoCompleteInteractionEvent getEvent();
    JDA getJda();
    String getFocusedContent();
    OptionType getFocusedType();
}
