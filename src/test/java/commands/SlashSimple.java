package commands;

import org.cmdfw.slash.SlashCommandContext;
import org.cmdfw.slash.builders.SlashCommand;
import org.cmdfw.slash.builders.SlashCommandBuilder;

import java.util.Objects;

public class SlashSimple implements SlashCommand {
    @Override
    public void register(SlashCommandBuilder builder) {
        builder.simple()
                .setName("juan")
                .setDescription("La vida es dura")
                .addArgument()
                .string()
                .setName("algo")
                .setDescription("Algo pa repetir")
                .finish();
    }

    @Override
    public void execute(SlashCommandContext context) throws Exception {
        String algo = context.getEvent().getInteraction().getOption("algo").getAsString();

        context.getInteraction().reply(String.format("Arg: %s", algo)).queue();
    }
}
