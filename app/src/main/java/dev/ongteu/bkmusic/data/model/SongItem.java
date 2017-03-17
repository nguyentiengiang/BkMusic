package dev.ongteu.bkmusic.data.model;

/**
 * Created by TienGiang on 26/9/2016.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SongItem {

    @SerializedName("songName")
    @Expose
    private String songName;
    @SerializedName("singer")
    @Expose
    private String singer;
    @SerializedName("singerUrl")
    @Expose
    private String singerUrl;
    @SerializedName("bgimage")
    @Expose
    private String bgimage;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("keyMp3")
    @Expose
    private String keyMp3;
    @SerializedName("mp3Url")
    @Expose
    private String mp3Url;
    @SerializedName("songUrl")
    @Expose
    private String songUrl;

    /**
     * No args constructor for use in serialization
     *
     */
    public SongItem() {
    }

    /**
     *
     * @param bgimage
     * @param singerUrl
     * @param singer
     * @param keyMp3
     * @param songName
     * @param avatar
     * @param mp3Url
     * @param songUrl
     */
    public SongItem(String songName, String singer, String singerUrl, String bgimage, String avatar, String keyMp3, String mp3Url, String songUrl) {
        super();
        this.songName = songName;
        this.singer = singer;
        this.singerUrl = singerUrl;
        this.bgimage = bgimage;
        this.avatar = avatar;
        this.keyMp3 = keyMp3;
        this.mp3Url = mp3Url;
        this.songUrl = songUrl;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public SongItem withSongName(String songName) {
        this.songName = songName;
        return this;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public SongItem withSinger(String singer) {
        this.singer = singer;
        return this;
    }

    public String getSingerUrl() {
        return singerUrl;
    }

    public void setSingerUrl(String singerUrl) {
        this.singerUrl = singerUrl;
    }

    public SongItem withSingerUrl(String singerUrl) {
        this.singerUrl = singerUrl;
        return this;
    }

    public String getBgimage() {
        return bgimage;
    }

    public void setBgimage(String bgimage) {
        this.bgimage = bgimage;
    }

    public SongItem withBgimage(String bgimage) {
        this.bgimage = bgimage;
        return this;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public SongItem withAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public String getKeyMp3() {
        return keyMp3;
    }

    public void setKeyMp3(String keyMp3) {
        this.keyMp3 = keyMp3;
    }

    public SongItem withKeyMp3(String keyMp3) {
        this.keyMp3 = keyMp3;
        return this;
    }

    public String getMp3Url() {
        return mp3Url;
    }

    public void setMp3Url(String mp3Url) {
        this.mp3Url = mp3Url;
    }

    public SongItem withMp3Url(String mp3Url) {
        this.mp3Url = mp3Url;
        return this;
    }

    public String getSongUrl() {
        return songUrl;
    }

    public void setSongUrl(String songUrl) {
        this.songUrl = songUrl;
    }

    public SongItem withSongUrl(String songUrl) {
        this.songUrl = songUrl;
        return this;
    }

}