package commands;

import kotlin.jvm.internal.Intrinsics;
import net.dv8tion.jda.api.interactions.commands.Command;
import org.cmdfw.exceptions.UnsupportedChoicesException;
import org.cmdfw.slash.AutocompleteContext;
import org.cmdfw.slash.NameValue;
import org.cmdfw.slash.SlashCommandContext;
import org.cmdfw.slash.builders.SlashCommand;
import org.cmdfw.slash.builders.SlashCommandBuilder;

public class Managed implements SlashCommand {
    @Override
    public void register(SlashCommandBuilder builder) {
        try {
            builder.setName("managed")
                    .setDescription("A test of managed command")
                    .addArgument()
                    .string()
                    .setName("c")
                    .setDescription("Choice test")
                    .addChoices(new NameValue("Juan", "Juan"), new NameValue("Pedro", "Antonio"))
                    .finish()
                    .addArgument()
                    .string()
                    .setName("autoc")
                    .setDescription("Autocompletable argument")
                    .autocompleteManaged(this::provideAutocomplete)
                    .finish();
        } catch (UnsupportedChoicesException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void execute(SlashCommandContext context) throws Exception {
        String c = context.getInteraction().getOption("c").getAsString();
        String autoc = context.getInteraction().getOption("autoc").getAsString();
        System.out.printf("C: %s, Autoc: %s%n", c, autoc);
    }

    Command.Choice[] provideAutocomplete(AutocompleteContext context) {
        return new Command.Choice[] {
                new Command.Choice("Si", "Seguro"),
                new Command.Choice("No", "Ni de broma")
        };
    }
}
