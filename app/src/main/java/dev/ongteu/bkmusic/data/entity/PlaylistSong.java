package dev.ongteu.bkmusic.data.entity;

import com.github.gfx.android.orma.annotation.Column;
import com.github.gfx.android.orma.annotation.PrimaryKey;
import com.github.gfx.android.orma.annotation.Table;

/**
 * Created by TienGiang on 16/3/2017.
 */

@Table
public class PlaylistSong {

    @Column
    private long playListId;

    @Column
    private String songId;

    public PlaylistSong() {
    }

    public PlaylistSong(long playListId, String songId) {
        this.playListId = playListId;
        this.songId = songId;
    }

    public long getPlayListId() {
        return playListId;
    }

    public void setPlayListId(long playListId) {
        this.playListId = playListId;
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }
}
