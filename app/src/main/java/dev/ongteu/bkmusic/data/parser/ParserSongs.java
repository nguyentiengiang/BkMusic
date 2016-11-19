package dev.ongteu.bkmusic.data.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.List;

import dev.ongteu.bkmusic.data.model.SongItem;

/**
 * Created by TienGiang on 2/10/2016.
 */

public class ParserSongs implements IResponse {

    protected List<SongItem> listSongs = null;

    @Override
    public void success(String json) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        Gson gson = gsonBuilder.create();
        listSongs = Arrays.asList(gson.fromJson(json, SongItem[].class));
    }

    @Override
    public void fail(Exception ex) {

    }
}
