package dev.ongteu.bkmusic.data.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.List;

import dev.ongteu.bkmusic.data.model.AlbumItem;

/**
 * Created by TienGiang on 27/9/2016.
 */

public class ParserPopularAlbum implements IResponse {

    protected List<AlbumItem> listPopularAlbum = null;

    @Override
    public void success(String json) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        Gson gson = gsonBuilder.create();

        listPopularAlbum = Arrays.asList(gson.fromJson(json, AlbumItem[].class));
    }

    @Override
    public void fail(Exception ex) {

    }
}
