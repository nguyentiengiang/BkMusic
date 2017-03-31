package dev.ongteu.bkmusic.data.entity;

import com.github.gfx.android.orma.annotation.Column;
import com.github.gfx.android.orma.annotation.PrimaryKey;
import com.github.gfx.android.orma.annotation.Table;

/**
 * Created by TienGiang on 16/3/2017.
 */

@Table
public class Playlist {

    @PrimaryKey(autoincrement = true)
    private long id;

    @Column
    private String name;

    private int count;

    public Playlist(){
    }

    public Playlist(String name) {
        this.name = name;
    }

    public Playlist(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
