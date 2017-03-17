package dev.ongteu.bkmusic.data.entity;

import com.orm.SugarRecord;

import java.util.List;

/**
 * Created by TienGiang on 16/3/2017.
 */

public class Playlist extends SugarRecord {
    private Long id;
    private String name;

    public Playlist(){
    }

    public Playlist(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
