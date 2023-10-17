package commands;

import org.cmdfw.slash.builders.SlashCommand;
import org.cmdfw.slash.builders.SlashCommandBuilder;

public class SlashSubcommandGroup implements SlashCommand {
    @Override
    public void register(SlashCommandBuilder builder) {
        builder.addSubcommandGroup()
                .setName("upgroup")
                .setDescription("Testing for subcommand group")
                .addGroup()
                .setName("ingroup")
                .setDescription("The group inside")
                .addCommand(new InnerSlashTest());
    }
}
