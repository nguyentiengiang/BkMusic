package dev.ongteu.bkmusic.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TienGiang on 26/9/2016.
 */

public class AlbumItem {

    @SerializedName("albumArt")
    @Expose
    private String albumArt;
    @SerializedName("albumName")
    @Expose
    private String albumName;
    @SerializedName("albumUrl")
    @Expose
    private String albumUrl;
    @SerializedName("singer")
    @Expose
    private String singer;
    @SerializedName("singerUrl")
    @Expose
    private String singerUrl;

    /**
     * No args constructor for use in serialization
     *
     */
    public AlbumItem() {
    }

    /**
     *
     * @param albumName
     * @param singerUrl
     * @param albumUrl
     * @param singer
     * @param albumArt
     */
    public AlbumItem(String albumArt, String albumName, String albumUrl, String singer, String singerUrl) {
        super();
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
