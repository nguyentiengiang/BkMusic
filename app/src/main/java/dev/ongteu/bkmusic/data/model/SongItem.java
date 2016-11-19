package dev.ongteu.bkmusic.data.model;

/**
 * Created by TienGiang on 26/9/2016.
 */

public class SongItem {
    private String songName;
    private String singer;
    private String singerUrl;
    private String bgimage;
    private String avatar;
    private String keyMp3;
    private String mp3Url;
    private String songUrl;

    public SongItem(String songName, String singer, String singerUrl, String bgimage, String avatar, String keyMp3, String mp3Url, String songUrl) {
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

    public String getBgimage() {
        return bgimage;
    }

    public void setBgimage(String bgimage) {
        this.bgimage = bgimage;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getKeyMp3() {
        return keyMp3;
    }

    public void setKeyMp3(String keyMp3) {
        this.keyMp3 = keyMp3;
    }

    public String getMp3Url() {
        return mp3Url;
    }

    public void setMp3Url(String mp3Url) {
        this.mp3Url = mp3Url;
    }

    public String getSongUrl() {
        return songUrl;
    }

    public void setSongUrl(String songUrl) {
        this.songUrl = songUrl;
    }
}
