package org.cmdfw.slash;

import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import org.cmdfw.exceptions.UnsupportedChoicesException;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.function.Function;

public abstract class SlashCommandArgument {
    SlashCommandArgument string() {
        return setOptionType(OptionType.STRING);
    }

    SlashCommandArgument integer() {
        return setOptionType(OptionType.INTEGER);
    }

    SlashCommandArgument bool() {
        return setOptionType(OptionType.BOOLEAN);
    }

    SlashCommandArgument user() {
        return setOptionType(OptionType.USER);
    }

    SlashCommandArgument channel() {
        return setOptionType(OptionType.CHANNEL);
    }

    SlashCommandArgument role() {
        return setOptionType(OptionType.ROLE);
    }

    SlashCommandArgument mentionable() {
        return setOptionType(OptionType.MENTIONABLE);
    }

    SlashCommandArgument number() {
        return setOptionType(OptionType.NUMBER);
    }

    SlashCommandArgument attachment() {
        return setOptionType(OptionType.ATTACHMENT);
    }
    SlashCommandArgument autocompleteManaged(Function<AutocompleteContext, Command.Choice[]> provider) {
        return autocomplete(cx -> {
            cx.getEvent().replyChoices(provider.apply(cx)).queue();
            return null;
        });
    }
    abstract SlashCommandArgument autocomplete(Function<AutocompleteContext, Void> provider);
    abstract SlashCommandArgument setOptionType(@NotNull OptionType t);
    abstract SlashCommandArgument setName(@NotNull String name);
    abstract SlashCommandArgument setDescription(@NotNull String description);
    abstract SlashCommandArgument addChoices(NameValue... choices) throws UnsupportedChoicesException;
    abstract SlashCommandBuilder finish();
}
