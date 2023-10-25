package org.cmdfw.extras.music

import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers
import net.dv8tion.jda.api.entities.Guild
import java.util.concurrent.ConcurrentHashMap

class GlobalMusicManager {
    private val manager = DefaultAudioPlayerManager()
    private val players = HashMap<Long, GuildMusicManager>()

    init {
        AudioSourceManagers.registerRemoteSources(manager)
        AudioSourceManagers.registerLocalSource(manager)
    }

    @Synchronized
    fun getGuildPlayer(guild: Guild) : GuildMusicManager {
        return getGuildPlayer(guild.id.toLong())
    }

    @Synchronized
    fun getGuildPlayer(guild: Long) : GuildMusicManager {
        return if(players.contains(guild)) {
            players[guild]!!
        } else {
            val manager = GuildMusicManager(manager)
            players[guild] = manager
            manager
        }
    }

    fun getTrackSearchHelper() = TrackSearchHelper(manager)
}