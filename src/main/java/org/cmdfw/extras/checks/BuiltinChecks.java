package org.cmdfw.extras.checks;

import org.cmdfw.message.MessageCommandContext;
import org.jetbrains.annotations.NotNull;

public class BuiltinChecks {
    static boolean onlyGuilds(@NotNull MessageCommandContext context) {
        return context.getEvent().isFromGuild();
    }
}
