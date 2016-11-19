package dev.ongteu.bkmusic.data.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.List;

import dev.ongteu.bkmusic.data.model.HotSongItem;

/**
 * Created by TienGiang on 25/9/2016.
 */

public class ParserHotMusic implements IResponse {

    protected List<HotSongItem> listHotSong = null;
    @Override
    public void success(String json) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        Gson gson = gsonBuilder.create();

        listHotSong = Arrays.asList(gson.fromJson(json, HotSongItem[].class));
    }

    @Override
    public void fail(Exception ex) {

    }
}
