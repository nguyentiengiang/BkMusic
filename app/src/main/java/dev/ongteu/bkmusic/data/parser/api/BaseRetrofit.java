package dev.ongteu.bkmusic.data.parser.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dev.ongteu.bkmusic.utils.Constant;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by TienGiang on 16/3/2017.
 */

public class BaseRetrofit {
    public static Retrofit instance(){
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
        return new Retrofit.Builder()
                .baseUrl(Constant.URL_HOST)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
}
