package commands;

import org.cmdfw.slash.SlashCommandContext;
import org.cmdfw.slash.builders.SimpleGroupBuilder;
import org.cmdfw.slash.builders.SlashCommand;
import org.cmdfw.slash.builders.SlashCommandBuilder;
import org.cmdfw.slash.builders.SubCommandGroup;

public class SlashSimpleGroup implements SubCommandGroup {
    @Override
    public void register(SimpleGroupBuilder builder) {
        builder.setName("simplet")
                .setDescription("Testing for simple group")
                .addCommand(new InnerSlashTest())
                .addCommand()
                .setHandler(this::handleSub)
                .setName("vie")
                .setDescription("la vie e dura")
                .addArgument()
                .string()
                .setName("juan")
                .setDescription("la via e ura")
                .setRequired(false)
                .finish();
    }


    void handleSub(SlashCommandContext c) {
        c.getInteraction().reply("La via e ura, pero ma ura e la verura").queue();
    }
}
