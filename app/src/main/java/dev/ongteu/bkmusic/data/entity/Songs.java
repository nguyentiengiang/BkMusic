package dev.ongteu.bkmusic.data.entity;

/**
 * Created by TienGiang on 26/9/2016.
 */

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

public class Songs extends SugarRecord {

    private Long id;

    private String songName;

    private String singer;

    private String singerUrl;

    private String bgimage;

    private String avatar;

    @Unique
    private String keyMp3;

    private String mp3Url;

    private String songUrl;

    private String fileName;

    private int isUserLocal;

    /**
     * No args constructor for use in serialization
     *
     */
    public Songs() {
    }

    /**
     * For create new Song
     *
     * @param songName
     * @param singer
     * @param singerUrl
     * @param bgimage
     * @param avatar
     * @param keyMp3
     * @param mp3Url
     * @param songUrl
     * @param isUserLocal
     */
    public Songs(String songName, String singer, String singerUrl, String bgimage, String avatar, String keyMp3, String mp3Url, String songUrl, String fileName, int isUserLocal) {
        super();
        this.songName = songName;
        this.singer = singer;
        this.singerUrl = singerUrl;
        this.bgimage = bgimage;
        this.avatar = avatar;
        this.keyMp3 = keyMp3;
        this.mp3Url = mp3Url;
        this.songUrl = songUrl;
        this.fileName = fileName;
        this.isUserLocal = isUserLocal;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public Songs withSongName(String songName) {
        this.songName = songName;
        return this;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public Songs withSinger(String singer) {
        this.singer = singer;
        return this;
    }

    public String getSingerUrl() {
        return singerUrl;
    }

    public void setSingerUrl(String singerUrl) {
        this.singerUrl = singerUrl;
    }

    public Songs withSingerUrl(String singerUrl) {
        this.singerUrl = singerUrl;
        return this;
    }

    public String getBgimage() {
        return bgimage;
    }

    public void setBgimage(String bgimage) {
        this.bgimage = bgimage;
    }

    public Songs withBgimage(String bgimage) {
        this.bgimage = bgimage;
        return this;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Songs withAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public String getKeyMp3() {
        return keyMp3;
    }

    public void setKeyMp3(String keyMp3) {
        this.keyMp3 = keyMp3;
    }

    public Songs withKeyMp3(String keyMp3) {
        this.keyMp3 = keyMp3;
        return this;
    }

    public String getMp3Url() {
        return mp3Url;
    }

    public void setMp3Url(String mp3Url) {
        this.mp3Url = mp3Url;
    }

    public Songs withMp3Url(String mp3Url) {
        this.mp3Url = mp3Url;
        return this;
    }

    public String getSongUrl() {
        return songUrl;
    }

    public void setSongUrl(String songUrl) {
        this.songUrl = songUrl;
    }

    public Songs withSongUrl(String songUrl) {
        this.songUrl = songUrl;
        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Songs withFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public int getIsUserLocal() {
        return isUserLocal;
    }

    public void setIsUserLocal(int isUserLocal) {
        this.isUserLocal = isUserLocal;
    }

    public Songs withIsUserLocal(int isUserLocal) {
        this.isUserLocal = isUserLocal;
        return this;
    }

}