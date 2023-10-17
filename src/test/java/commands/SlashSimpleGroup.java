package commands;

import org.cmdfw.slash.SlashCommandContext;
import org.cmdfw.slash.builders.SlashCommand;
import org.cmdfw.slash.builders.SlashCommandBuilder;

public class SlashSimpleGroup implements SlashCommand {
    @Override
    public void register(SlashCommandBuilder builder) {
        builder.addSubcommands()
                .setName("simplet")
                .setDescription("Testing for simple group")
                .addCommand(new InnerSlashTest());
    }
}
