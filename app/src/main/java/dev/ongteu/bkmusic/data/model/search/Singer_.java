
package dev.ongteu.bkmusic.data.model.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Singer_ {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("img")
    @Expose
    private String img;
    @SerializedName("url")
    @Expose
    private String url;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Singer_() {
    }

    /**
     * 
     * @param name
     * @param img
     * @param url
     */
    public Singer_(String name, String img, String url) {
        super();
        this.name = name;
        this.img = img;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
