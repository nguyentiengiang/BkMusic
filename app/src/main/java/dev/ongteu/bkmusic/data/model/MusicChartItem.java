package dev.ongteu.bkmusic.data.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TienGiang on 26/9/2016.
 */

public class MusicChartItem {
    @SerializedName("playAllUrl")
    @Expose
    private String playAllUrl;
    @SerializedName("songs")
    @Expose
    private List<SongChart> songs = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public MusicChartItem() {
    }

    /**
     *
     * @param songs
     * @param playAllUrl
     */
    public MusicChartItem(String playAllUrl, List<SongChart> songs) {
        super();
        this.playAllUrl = playAllUrl;
        this.songs = songs;
    }

    public String getPlayAllUrl() {
        return playAllUrl;
    }

    public void setPlayAllUrl(String playAllUrl) {
        this.playAllUrl = playAllUrl;
    }

    public List<SongChart> getSongs() {
        return songs;
    }

    public void setSongs(List<SongChart> songs) {
        this.songs = songs;
    }

    public class SongChart {

        @SerializedName("albumArt")
        @Expose
        private String albumArt;
        @SerializedName("songName")
        @Expose
        private String songName;
        @SerializedName("songUrl")
        @Expose
        private String songUrl;
        @SerializedName("singer")
        @Expose
        private String singer;
        @SerializedName("singerUrl")
        @Expose
        private String singerUrl;

        /**
         * No args constructor for use in serialization
         *
         */
        public SongChart() {
        }

        /**
         *
         * @param singerUrl
         * @param singer
         * @param songName
         * @param albumArt
         * @param songUrl
         */
        public SongChart(String albumArt, String songName, String songUrl, String singer, String singerUrl) {
            super();
            this.albumArt = albumArt;
            this.songName = songName;
            this.songUrl = songUrl;
            this.singer = singer;
            this.singerUrl = singerUrl;
        }

        public String getAlbumArt() {
            return albumArt;
        }

        public void setAlbumArt(String albumArt) {
            this.albumArt = albumArt;
        }

        public String getSongName() {
            return songName;
        }

        public void setSongName(String songName) {
            this.songName = songName;
        }

        public String getSongUrl() {
            return songUrl;
        }

        public void setSongUrl(String songUrl) {
            this.songUrl = songUrl;
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
    }
}
