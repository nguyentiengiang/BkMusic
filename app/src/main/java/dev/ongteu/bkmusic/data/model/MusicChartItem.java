package dev.ongteu.bkmusic.data.model;

import java.util.List;

/**
 * Created by TienGiang on 26/9/2016.
 */

public class MusicChartItem {
    private String playAllUrl;
    private List<SongChart> songs;

    public MusicChartItem(String playAllUrl, List<SongChart> songs) {
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
        private String albumArt;
        private String songName;
        private String songUrl;
        private String singer;
        private String singerUrl;

        public SongChart(String albumArt, String songName, String songUrl, String singer, String singerUrl) {
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
