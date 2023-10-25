package org.cmdfw.extras.music;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import java.util.Iterator;

/**
 * An item inside the context of a guild.
 * @see Item
 */
public class GuildItem {
    private final Item item;
    private final GuildMusicManager manager;

    GuildItem(Item item, GuildMusicManager manager) {
        this.item = item;
        this.manager = manager;
    }

    /**
     * Returns the underlying item
     *
     * @return The underlying contained item
     */
    public Item getItem() {
        return item;
    }

    /**
     * Enqueues the underlying item to play inside the guild,
     * if the underlying item is a playlist, enqueues the whole playlist
     */
    public void enqueue() {
        if(item.isTrack()) {
            manager.getTrackScheduler().queue(item.getTrack());
        } else {
            for(AudioTrack t : item.getPlaylist().getTracks()) {
                manager.getTrackScheduler().queue(t);
            }
        }
    }

    /**
     * Forces the underlying item to play inside the guild,
     * if the underlying item is a playlist, forces the first
     * track to play and enqueues the rest of the playlist
     */
    public void forcePlay() {
        if(item.isTrack()) {
            manager.getTrackScheduler().queue(item.getTrack());
        } else {
            Iterator<AudioTrack> iter = item.getPlaylist().getTracks().iterator();

            if(iter.hasNext()) {
                manager.getTrackScheduler().queue(iter.next());
            }

            iter.forEachRemaining(track -> {
                manager.getTrackScheduler().queue(track);
            });
        }
    }
}
