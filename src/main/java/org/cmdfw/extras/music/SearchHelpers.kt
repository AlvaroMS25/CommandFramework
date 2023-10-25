package org.cmdfw.extras.music

import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager
import com.sedmelluq.discord.lavaplayer.track.AudioItem
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist
import com.sedmelluq.discord.lavaplayer.track.AudioTrack
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.runBlocking
import java.net.URL

private fun isUrl(url: String): Boolean {
    return try {
        URL(url)
        true
    } catch (_: Exception) {
        false
    }
}

private fun getSearchQuery(source: Source, query: String): String {
    return if(isUrl(query)) {
        query
    } else {
        "${source.rep}$query"
    }
}

class TrackSearchHelper(
    private val manager: AudioPlayerManager,
) {
    fun search(source: Source, query: String): Item? {
        val chan = Channel<AudioItem?>(1)
        val resultHandler = DefaultLoadResultHandler(chan)
        manager.loadItem(getSearchQuery(source, query), resultHandler)

        val item = mapItem(runBlocking {
            val v = chan.receive()
            v
        })

        if(resultHandler.exception != null)
            throw resultHandler.exception!!

        return item
    }

    companion object {
        fun mapItem(track: AudioItem?): Item? {
            return if(track is AudioTrack)
                Item(track)
            else if(track is AudioPlaylist)
                Item(track)
            else null
        }
    }
}

class GuildTrackSearchHelper(
    private val manager: AudioPlayerManager,
    private val guildPlayer: GuildMusicManager
) {
    fun search(source: Source, query: String): GuildItem? {
        val item = TrackSearchHelper(manager)
            .search(source, query)

        return item?.let {
            GuildItem(it, guildPlayer)
        }
    }
}