package dev.ongteu.bkmusic.data.model;

/**
 * Created by TienGiang on 26/9/2016.
 */

public class AlbumItem {
    private String albumArt;
    private String albumName;
    private String albumUrl;
    private String singer;
    private String singerUrl;

    public AlbumItem(String albumArt, String albumName, String albumUrl, String singer, String singerUrl) {
        this.albumArt = albumArt;
        this.albumName = albumName;
        this.albumUrl = albumUrl;
        this.singer = singer;
        this.singerUrl = singerUrl;
    }

    public String getAlbumArt() {
        return albumArt;
    }

    public void setAlbumArt(String albumArt) {
        this.albumArt = albumArt;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumUrl() {
        return albumUrl;
    }

    public void setAlbumUrl(String albumUrl) {
        this.albumUrl = albumUrl;
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
}
