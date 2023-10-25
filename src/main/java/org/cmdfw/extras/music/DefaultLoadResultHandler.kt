package org.cmdfw.extras.music

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException
import com.sedmelluq.discord.lavaplayer.track.AudioItem
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist
import com.sedmelluq.discord.lavaplayer.track.AudioTrack
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.runBlocking
import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingQueue

class DefaultLoadResultHandler(private val channel: Channel<AudioItem?>): AudioLoadResultHandler {
    internal var exception: FriendlyException? = null

    override fun trackLoaded(track: AudioTrack?) {
        if(track != null)
            send(track)

        send(null)
    }

    override fun playlistLoaded(playlist: AudioPlaylist?) {
        if(playlist == null)
            send(null)

        send(playlist)
    }

    override fun noMatches() {
        send(null)
    }

    override fun loadFailed(exception: FriendlyException?) {
        this.exception = exception
        send(null)
    }

    private fun send(item: AudioItem?) = channel.trySendBlocking(item).getOrThrow()
}