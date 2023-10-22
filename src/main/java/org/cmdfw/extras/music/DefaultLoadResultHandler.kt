package org.cmdfw.extras.music

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist
import com.sedmelluq.discord.lavaplayer.track.AudioTrack
import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingQueue

class DefaultLoadResultHandler: AudioLoadResultHandler {
    internal val queue: MutableList<AudioTrack> = mutableListOf()
    internal var exception: FriendlyException? = null

    override fun trackLoaded(track: AudioTrack?) {
        if(track != null)
            queue.add(track)
    }

    override fun playlistLoaded(playlist: AudioPlaylist?) {
        if(playlist == null)
            return

        val track: AudioTrack? = if(playlist.selectedTrack != null)
            playlist.selectedTrack
        else if(playlist.tracks.isNotEmpty())
            playlist.tracks[0]
        else null

        if(track != null)
            queue.add(track)
    }

    override fun noMatches() {}

    override fun loadFailed(exception: FriendlyException?) {
        this.exception = exception
    }

    internal fun mapTracks(): List<Item> {
        TODO()
    }
}