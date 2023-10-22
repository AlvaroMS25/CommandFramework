package org.cmdfw.extras.music;

import com.sedmelluq.discord.lavaplayer.track.AudioItem;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import java.util.Optional;

public class Item {
    private AudioTrack track;
    private AudioPlaylist playlist;

    Item(AudioTrack track) {
        this.track = track;
    }

    Item(AudioPlaylist playlist) {
        this.playlist = playlist;
    }

    public boolean isPlaylist() {
        return playlist != null;
    }

    public boolean isTrack() {
        return track != null;
    }

    public AudioTrack getTrack() {
        if(isTrack())
            return track;
        else
            throw new NullPointerException();
    }

    public AudioPlaylist getPlaylist() {
        if(isPlaylist())
            return playlist;
        else
            throw new NullPointerException();
    }

    public AudioTrack getFirst() {
        if(isTrack())
            return track;
        else
            if(playlist.getSelectedTrack() != null)
                return playlist.getSelectedTrack();
            else
                return playlist.getTracks().get(0);
    }
}
