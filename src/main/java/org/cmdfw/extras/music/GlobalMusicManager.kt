package org.cmdfw.extras.music

import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers
import net.dv8tion.jda.api.entities.Guild

/**
 * A manager which contains and manages all the players
 * from different guilds
 */
class GlobalMusicManager {
    private val manager = DefaultAudioPlayerManager()
    private val players = HashMap<Long, GuildMusicManager>()

    init {
        AudioSourceManagers.registerRemoteSources(manager)
        AudioSourceManagers.registerLocalSource(manager)
    }

    /**
     * Retrieves the voice manager for the provided guild, if
     * no manager is present, a new one will be created.
     *
     * @param guild The guild of the voice manager
     * @return The voice manager of the specific guild
     */
    @Synchronized
    fun getGuildPlayer(guild: Guild) : GuildMusicManager {
        return getGuildPlayer(guild.id.toLong())
    }

    /**
     * Retrieves the voice manager for the provided guild, if
     * no manager is present, a new one will be created.
     *
     * @param guild The guild id of the voice manager
     * @return The voice manager of the specific guild
     */
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

    /**
     * Returns a helper to search tracks from supported sources
     *
     * @return The helper to search with
     */
    fun getTrackSearchHelper() = TrackSearchHelper(manager)
}