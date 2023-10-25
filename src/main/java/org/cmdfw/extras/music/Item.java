package org.cmdfw.extras.music;

import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import java.util.Optional;

/**
 * An item containing either a single track or an audio track.
 *
 * @see AudioTrack
 * @see AudioPlaylist
 */
public class Item {
    private AudioTrack track;
    private AudioPlaylist playlist;

    Item(AudioTrack track) {
        this.track = track;
    }

    Item(AudioPlaylist playlist) {
        this.playlist = playlist;
    }

    /**
     * Checks whether the item contains a playlist
     *
     * @return If the item contains a playlist
     */
    public boolean isPlaylist() {
        return playlist != null;
    }

    /**
     * Checks whether the item contains a single track
     *
     * @return If the item contains a single track
     */
    public boolean isTrack() {
        return track != null;
    }

    /**
     * Gets the inner audio track, if the item contains it.
     *
     * @throws NullPointerException if the item doesn't contain an audio track.
     * @return The underlying audio track, if present.
     */
    public AudioTrack getTrack() {
        if(isTrack())
            return track;
        else
            throw new NullPointerException();
    }

    /**
     * Gets the inner playlist, if the item contains it.
     *
     * @throws NullPointerException if the item doesn't contain a playlist.
     * @return The underlying playlist, if present.
     */
    public AudioPlaylist getPlaylist() {
        if(isPlaylist())
            return playlist;
        else
            throw new NullPointerException();
    }
}
