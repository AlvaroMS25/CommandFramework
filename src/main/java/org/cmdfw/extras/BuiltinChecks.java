package org.cmdfw.extras;

import org.cmdfw.message.MessageCommandContext;
import org.jetbrains.annotations.NotNull;

public class BuiltinChecks {
    public static boolean onlyGuilds(@NotNull MessageCommandContext context) {
        return context.getEvent().isFromGuild();
    }

    public static boolean userConnectedToVoiceChannel(@NotNull MessageCommandContext context) {
        if (!onlyGuilds(context))
            throw new UnsupportedOperationException("Check cannot be executed outside guilds");

        return InternalBuiltinChecks.userConnectedToVoiceChannel(context);
    }
}
