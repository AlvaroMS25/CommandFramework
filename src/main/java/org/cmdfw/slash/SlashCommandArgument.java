package org.cmdfw.slash;

import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import org.cmdfw.exceptions.UnsupportedChoicesException;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.function.Function;

public interface SlashCommandArgument {
    default SlashCommandArgument string() {
        return setOptionType(OptionType.STRING);
    }

    default SlashCommandArgument integer() {
        return setOptionType(OptionType.INTEGER);
    }

    default SlashCommandArgument bool() {
        return setOptionType(OptionType.BOOLEAN);
    }

    default SlashCommandArgument user() {
        return setOptionType(OptionType.USER);
    }

    default SlashCommandArgument channel() {
        return setOptionType(OptionType.CHANNEL);
    }

    default SlashCommandArgument role() {
        return setOptionType(OptionType.ROLE);
    }

    default SlashCommandArgument mentionable() {
        return setOptionType(OptionType.MENTIONABLE);
    }

    default SlashCommandArgument number() {
        return setOptionType(OptionType.NUMBER);
    }

    default SlashCommandArgument attachment() {
        return setOptionType(OptionType.ATTACHMENT);
    }
    default SlashCommandArgument autocompleteManaged(Function<AutocompleteContext, Command.Choice[]> provider) {
        return autocomplete(cx -> {
            cx.getEvent().replyChoices(provider.apply(cx)).queue();
            return null;
        });
    }
    SlashCommandArgument autocomplete(Function<AutocompleteContext, Void> provider);
    SlashCommandArgument setOptionType(@NotNull OptionType t);
    SlashCommandArgument setName(@NotNull String name);
    SlashCommandArgument setDescription(@NotNull String description);
    SlashCommandArgument addChoices(NameValue... choices) throws UnsupportedChoicesException;
    SlashCommandBuilder finish();
}
