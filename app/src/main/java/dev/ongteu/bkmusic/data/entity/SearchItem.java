package dev.ongteu.bkmusic.data.entity;

/**
 * Created by TienGiang on 01/04/2017.
 */

public class SearchItem {

    private String songName;

    private String artistName;

    private String songUrl;

    private int iconStatus;

    public SearchItem() {
    }

    public SearchItem(String songName, String artistName, String songUrl, int iconStatus) {
        this.songName = songName;
        this.artistName = artistName;
        this.songUrl = songUrl;
        this.iconStatus = iconStatus;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getSongUrl() {
        return songUrl;
    }

    public void setSongUrl(String songUrl) {
        this.songUrl = songUrl;
    }

    public int getIconStatus() {
        return iconStatus;
    }

    public void setIconStatus(int iconStatus) {
        this.iconStatus = iconStatus;
    }

    @Override
    public String toString() {
        return songName + " - " + artistName;
    }

}
