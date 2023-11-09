package org.cmdfw.extras

import org.cmdfw.message.MessageCommandContext
import org.cmdfw.slash.SlashCommandContext

internal class InternalBuiltinChecks {
    companion object {
        @JvmStatic
        fun userConnectedToVoiceChannel(context: MessageCommandContext): Boolean {
            return context.event.member
                ?.voiceState
                ?.channel != null
        }

        @JvmStatic
        fun userConnectedToVoiceChannel(context: SlashCommandContext): Boolean {
            return context.interaction.member
                ?.voiceState
                ?.channel != null
        }
    }
}