package dev.ongteu.bkmusic.data.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.List;

import dev.ongteu.bkmusic.data.model.CategoryItem;

/**
 * Created by TienGiang on 26/9/2016.
 */

public class ParserCategories implements IResponse {

    protected List<CategoryItem> listCategory = null;
    @Override
    public void success(String json) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        Gson gson = gsonBuilder.create();

        listCategory = Arrays.asList(gson.fromJson(json, CategoryItem[].class));
    }

    @Override
    public void fail(Exception ex) {

    }
}
