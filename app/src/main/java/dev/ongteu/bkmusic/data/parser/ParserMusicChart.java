package dev.ongteu.bkmusic.data.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import dev.ongteu.bkmusic.data.model.MusicChartItem;

/**
 * Created by TienGiang on 27/9/2016.
 */

public class ParserMusicChart implements IResponse {

    private MusicChartItem musicChartItem = null;
    protected String playAllUrl = null;
    protected List<MusicChartItem.SongChart> listSongOnChart = null;

    @Override
    public void success(String json) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        Gson gson = gsonBuilder.create();

        musicChartItem = gson.fromJson(json, MusicChartItem.class);
        playAllUrl = musicChartItem.getPlayAllUrl();
        listSongOnChart = musicChartItem.getSongs();
    }

    @Override
    public void fail(Exception ex) {

    }
}
