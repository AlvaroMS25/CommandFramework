package org.cmdfw.extras.music;

public class GuildItem {
    private Item item;
    private GuildMusicManager manager;

    GuildItem(Item item, GuildMusicManager manager) {
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
