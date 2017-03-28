
package dev.ongteu.bkmusic.data.model.search;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Song {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("singer")
    @Expose
    private List<Singer> singer = null;
    @SerializedName("url")
    @Expose
    private String url;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Song() {
    }

    /**
     * 
     * @param id
     * @param name
     * @param singer
     * @param url
     */
    public Song(String id, String name, List<Singer> singer, String url) {
        super();
        this.id = id;
        this.name = name;
        this.singer = singer;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Singer> getSinger() {
        return singer;
    }

    public void setSinger(List<Singer> singer) {
        this.singer = singer;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
