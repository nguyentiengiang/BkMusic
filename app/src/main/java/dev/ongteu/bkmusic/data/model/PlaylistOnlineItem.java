package dev.ongteu.bkmusic.data.model;

import java.util.List;

/**
 * Created by TienGiang on 2/10/2016.
 */

public class PlaylistOnlineItem {
    private int id;
    private String name;
    private String url;
    private List<SongItem> songList;

    public PlaylistOnlineItem(int id, String name, String url, List<SongItem> songList) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.songList = songList;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<SongItem> getSongList() {
        return songList;
    }

    public void setSongList(List<SongItem> songList) {
        this.songList = songList;
    }
}
