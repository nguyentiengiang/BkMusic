package dev.ongteu.bkmusic.data.model;

/**
 * Created by TienGiang on 25/9/2016.
 */

public class HotSongItem {
    private String songName;
    private String songUrl;
    private String singer;
    private String singerUrl;

    public HotSongItem() {
    }

    public HotSongItem(String songName, String songUrl, String singer, String singerUrl) {
        super();
        this.songName = songName;
        this.songUrl = songUrl;
        this.singer = singer;
        this.singerUrl = singerUrl;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongUrl() {
        return songUrl;
    }

    public void setSongUrl(String songUrl) {
        this.songUrl = songUrl;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getSingerUrl() {
        return singerUrl;
    }

    public void setSingerUrl(String singerUrl) {
        this.singerUrl = singerUrl;
    }

    @Override
    public String toString() {
        return this.songName;
    }
}
