package org.cmdfw.extras.music

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager
import net.dv8tion.jda.api.entities.Guild
import org.cmdfw.exceptions.NoGuildSetException


/**
 * A voice manager for a single guild.
 */
class GuildMusicManager(
    private val manager: AudioPlayerManager,
    private val guildId: Long
) {
    private val player: AudioPlayer = manager.createPlayer()
    private val scheduler: TrackScheduler = TrackScheduler(player)
    var guild: Guild? = null

    init {
        player.addListener(scheduler)
    }

    fun getPlayer(): AudioPlayer {
        return player
    }

    /**
     * Gets the underlying track scheduler, used to play tracks
     *
     * @return The underlying track scheduler
     */
    fun getTrackScheduler(): TrackScheduler {
        return scheduler
    }

    /**
     * Gets an audio sender for the current guild, wrapping the guild's player
     *
     * @return The audio sender for the guild
     */
    fun getHandler(): AudioPlayerSendHandler {
        return AudioPlayerSendHandler(player)
    }

    /**
     * Returns a helper to search tracks from supported sources inside the guild's
     * context
     *
     * @return The helper to search with within the guild context
     */
    fun getTrackSearchHelper() = GuildTrackSearchHelper(manager, this)

    @Throws(NoGuildSetException::class)
    fun getUtils(): GuildVoiceUtils {
        if(guild == null)
            throw NoGuildSetException(guildId)

        return GuildVoiceUtils(this)
    }
}