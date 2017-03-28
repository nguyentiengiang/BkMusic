
package dev.ongteu.bkmusic.data.model.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Playlist {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("img")
    @Expose
    private String img;
    @SerializedName("singer")
    @Expose
    private List<Singer__> singer = null;
    @SerializedName("url")
    @Expose
    private String url;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Playlist() {
    }

    /**
     * 
     * @param name
     * @param img
     * @param singer
     * @param url
     */
    public Playlist(String name, String img, List<Singer__> singer, String url) {
        super();
        this.name = name;
        this.img = img;
        this.singer = singer;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public List<Singer__> getSinger() {
        return singer;
    }

    public void setSinger(List<Singer__> singer) {
        this.singer = singer;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
