package org.cmdfw.extras.music

import net.dv8tion.jda.api.audio.hooks.ConnectionStatus
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.entities.channel.middleman.AudioChannel
import org.cmdfw.exceptions.AlreadyConnectedToVoiceException
import org.cmdfw.exceptions.NotConnectedToVoiceException

class GuildVoiceUtils(
    private val manager: GuildMusicManager,
) {
    private val guild: Guild = manager.guild!!

    @Throws(AlreadyConnectedToVoiceException::class)
    fun joinChannel(channel: AudioChannel): Boolean {
        val audioManager = guild.audioManager

        if(audioManager.isConnected || audioManager.connectionStatus != ConnectionStatus.NOT_CONNECTED)
            throw AlreadyConnectedToVoiceException(guild)

        audioManager.sendingHandler = manager.getHandler()
        audioManager.openAudioConnection(channel)
        return true
    }

    @Throws(AlreadyConnectedToVoiceException::class)
    fun joinChannel(id: Long): Boolean {
        val channel = guild.getVoiceChannelById(id) ?: return false

        return joinChannel(channel)
    }


    @Throws(NotConnectedToVoiceException::class)
    fun leaveVoice() {
        if(!guild.audioManager.isConnected)
            throw NotConnectedToVoiceException(guild)

        guild.audioManager.closeAudioConnection()
    }


}