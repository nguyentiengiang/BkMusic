package dev.ongteu.bkmusic.data.model;

import java.util.List;

/**
 * Created by TienGiang on 2/10/2016.
 */

public class PlaylistOfflineItem {
    private int id;
    private String name;
    private List<SongItem> songList;

    public PlaylistOfflineItem(int id, String name, List<SongItem> songList) {
        this.id = id;
        this.name = name;
        this.songList = songList;
    }

    public PlaylistOfflineItem() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SongItem> getSongList() {
        return songList;
    }

    public void setSongList(List<SongItem> songList) {
        this.songList = songList;
    }
}
