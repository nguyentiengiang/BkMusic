package dev.ongteu.bkmusic.data.entity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by TienGiang on 30/3/2017.
 */

public class NotifyData {

    private String id;

    private String title;

    private String desc;

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public NotifyData(String id) {
        this.id = id;
    }

    public NotifyData(String id, String title, String desc) {
        this.id = id;
        this.title = title;
        this.desc = desc;
    }

    public String toJson() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }

}