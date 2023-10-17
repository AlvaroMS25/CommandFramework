package commands;

import org.cmdfw.slash.SlashCommandContext;
import org.cmdfw.slash.builders.SlashCommand;
import org.cmdfw.slash.builders.SlashCommandBuilder;

import java.util.Objects;

public class InnerSlashTest implements SlashCommand {
    @Override
    public void register(SlashCommandBuilder builder) {
        builder.simple()
                .setName("inner")
                .setDescription("Inner command")
                .addArgument()
                .string()
                .setName("item")
                .setDescription("Item to repeat")
                .finish();
    }

    @Override
    public void execute(SlashCommandContext context) throws Exception {
        String a = Objects.requireNonNull(context.getInteraction().getOption("item")).getAsString();

        context.getEvent().reply(String.format("You said: %s", a)).queue();
    }
}
