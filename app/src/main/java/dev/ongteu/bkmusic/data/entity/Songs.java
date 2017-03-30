package dev.ongteu.bkmusic.data.entity;

import com.github.gfx.android.orma.annotation.Column;
import com.github.gfx.android.orma.annotation.PrimaryKey;
import com.github.gfx.android.orma.annotation.Table;

/**
 * Created by TienGiang on 26/9/2016.
 */

@Table
public class Songs {

    @PrimaryKey(autoincrement = true)
    private long id;

    @Column
    private String songName;

    @Column
    private String singer;

    @Column
    private String singerUrl;

    @Column
    private String bgimage;

    @Column
    private String avatar;

    @Column(unique = true)
    private String keyMp3;

    @Column
    private String mp3Url;

    @Column
    private String songUrl;

    @Column
    private String fileName;

    @Column
    private int isUserLocal;

    /**
     * No args constructor for use in serialization
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getIsUserLocal() {
        return isUserLocal;
    }

    public void setIsUserLocal(int isUserLocal) {
        this.isUserLocal = isUserLocal;
    }
}