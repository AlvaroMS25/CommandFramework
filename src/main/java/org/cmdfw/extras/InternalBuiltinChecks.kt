package org.cmdfw.extras

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.audio.hooks.ConnectionStatus
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.entities.Member
import org.cmdfw.message.MessageCommandContext
import org.cmdfw.slash.SlashCommandContext

internal class InternalBuiltinChecks {
    companion object {
        @JvmStatic
        fun userConnectedToVoiceChannel(member: Member?): Boolean {
            return member
                ?.voiceState
                ?.channel != null
        }

        @JvmStatic
        fun botConnectedToVoiceChannel(jda: JDA, guild: Guild): Boolean {
            return guild.audioManager.isConnected
                    || guild.audioManager.connectionStatus != ConnectionStatus.NOT_CONNECTED
        }
    }
}