package org.cmdfw.extras;

import org.cmdfw.message.MessageCommandContext;
import org.cmdfw.slash.SlashCommandContext;
import org.cmdfw.slash.builders.SlashCommandBuilder;
import org.jetbrains.annotations.NotNull;

public class BuiltinChecks {
    public static boolean onlyGuilds(@NotNull MessageCommandContext context) {
        return context.getEvent().isFromGuild();
    }

    public static boolean userConnectedToVoiceChannel(@NotNull MessageCommandContext context) {
        if (!onlyGuilds(context))
            throw new UnsupportedOperationException("Check cannot be executed outside guilds");

        return InternalBuiltinChecks.userConnectedToVoiceChannel(context.getEvent().getMember());
    }

    public static boolean userConnectedToVoiceChannel(@NotNull SlashCommandContext context) {
        if(!context.getInteraction().isFromGuild())
            throw new UnsupportedOperationException("Check cannot be executed outside guilds");
        return InternalBuiltinChecks.userConnectedToVoiceChannel(context.getEvent().getMember());
    }
}
