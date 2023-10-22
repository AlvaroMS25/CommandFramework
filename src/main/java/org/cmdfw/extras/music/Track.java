package org.cmdfw.extras.music;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import java.util.Optional;

public class Track {
    private Item item;
    private GuildMusicManager manager;

    Track(Item item, GuildMusicManager manager) {
        this.item = item;
        this.manager = manager;
    }

    public Item getItem() {
        return item;
    }

    public void enqueue() {
        manager.getTrackScheduler().queue(item.getFirst());
    }

    public void forcePlay() {
        manager.getTrackScheduler().forcePlay(item.getFirst());
    }
}
