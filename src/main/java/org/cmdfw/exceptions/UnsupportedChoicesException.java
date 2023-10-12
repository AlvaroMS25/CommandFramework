package org.cmdfw.exceptions;

import net.dv8tion.jda.api.interactions.commands.OptionType;

public class UnsupportedChoicesException extends Exception {
    @Override
    public String getMessage() {
        return "Only String, Integer and Number arguments support choices";
    }

    public OptionType[] getOptionsSupportingChoices() {
        return new OptionType[] { OptionType.STRING, OptionType.INTEGER, OptionType.NUMBER };
    }
}
