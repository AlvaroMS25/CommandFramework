package org.cmdfw.extras.music

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager
import com.sedmelluq.discord.lavaplayer.track.AudioTrack
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
    private val manager: DefaultAudioPlayerManager,
) {
    fun search(source: Source, query: String): List<Item> {
        val resultHandler = DefaultLoadResultHandler()
        manager.loadItem(getSearchQuery(source, query), resultHandler)
        if(resultHandler.exception != null)
            throw resultHandler.exception!!
        return resultHandler.mapTracks()
    }
}

class GuildTrackSearchHelper(
    private val manager: DefaultAudioPlayerManager,
    private val guildPlayer: GuildMusicManager
) {
    fun search(source: Source, query: String): List<Track> {
        TODO()
    }
}