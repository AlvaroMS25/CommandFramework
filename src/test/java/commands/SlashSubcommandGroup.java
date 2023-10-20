package commands;

import org.cmdfw.slash.builders.SlashCommand;
import org.cmdfw.slash.builders.SlashCommandBuilder;
import org.cmdfw.slash.builders.SubCommandGroup;
import org.cmdfw.slash.builders.SubcommandGroupBuilder;

public class SlashSubcommandGroup implements SubCommandGroup {
    @Override
    public void register(SubcommandGroupBuilder builder) {
        builder.setName("upgroup")
                .setDescription("Testing for subcommand group")
                .addGroup()
                .setName("ingroup")
                .setDescription("The group inside")
                .addCommand(new InnerSlashTest());
    }
}
