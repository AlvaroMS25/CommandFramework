package org.cmdfw.extras.music

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager

class GuildMusicManager(
    manager: AudioPlayerManager
) {
    private val player: AudioPlayer
    private val scheduler: TrackScheduler

    init {
        player = manager.createPlayer()
        scheduler = TrackScheduler(player)
        player.addListener(scheduler)
    }

    fun getTrackScheduler(): TrackScheduler {
        return scheduler
    }

    fun getHandler(): AudioPlayerSendHandler {
        return AudioPlayerSendHandler(player)
    }

    fun getTrackSearchHelper(): GuildTrackSearchHelper {
        TODO()
    }
}