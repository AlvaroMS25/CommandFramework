package commands;

import org.cmdfw.slash.SlashCommandContext;
import org.cmdfw.slash.builders.SlashCommand;
import org.cmdfw.slash.builders.SlashCommandBuilder;

import java.util.Objects;

public class SlashSimple implements SlashCommand {
    @Override
    public void register(SlashCommandBuilder builder) {
        builder.simple()
                .setName("Juan")
                .setDescription("La vida es dura")
                .addArgument()
                .string()
                .setName("Algo")
                .setDescription("Algo pa repetir");
    }

    @Override
    public void execute(SlashCommandContext context) throws Exception {
        String algo = Objects.requireNonNull(context.getEvent().getInteraction().getOption("Algo")).getAsString();

        context.getInteraction().reply(String.format("Arg: %s", algo)).queue();
    }
}
