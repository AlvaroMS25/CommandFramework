package org.cmdfw.extras.music

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager
import kotlin.concurrent.thread

class GuildMusicManager(
    private val manager: AudioPlayerManager
) {
    private val player: AudioPlayer = manager.createPlayer()
    private val scheduler: TrackScheduler = TrackScheduler(player)

    init {
        player.addListener(scheduler)
    }

    fun getTrackScheduler(): TrackScheduler {
        return scheduler
    }

    fun getHandler(): AudioPlayerSendHandler {
        return AudioPlayerSendHandler(player)
    }

    fun getTrackSearchHelper() = GuildTrackSearchHelper(manager, this)
}