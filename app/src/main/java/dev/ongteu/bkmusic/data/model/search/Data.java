
package dev.ongteu.bkmusic.data.model.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("song")
    @Expose
    private List<Song> song = null;
    @SerializedName("singer")
    @Expose
    private List<Singer_> singer = null;
    @SerializedName("playlist")
    @Expose
    private List<Playlist> playlist = null;
    @SerializedName("video")
    @Expose
    private List<Video> video = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Data() {
    }

    /**
     * 
     * @param song
     * @param singer
     * @param playlist
     * @param video
     */
    public Data(List<Song> song, List<Singer_> singer, List<Playlist> playlist, List<Video> video) {
        super();
        this.song = song;
        this.singer = singer;
        this.playlist = playlist;
        this.video = video;
    }

    public List<Song> getSong() {
        return song;
    }

    public void setSong(List<Song> song) {
        this.song = song;
    }

    public List<Singer_> getSinger() {
        return singer;
    }

    public void setSinger(List<Singer_> singer) {
        this.singer = singer;
    }

    public List<Playlist> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(List<Playlist> playlist) {
        this.playlist = playlist;
    }

    public List<Video> getVideo() {
        return video;
    }

    public void setVideo(List<Video> video) {
        this.video = video;
    }

}
