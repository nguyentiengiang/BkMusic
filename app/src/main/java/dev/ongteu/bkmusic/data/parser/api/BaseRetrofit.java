package dev.ongteu.bkmusic.data.parser.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import dev.ongteu.bkmusic.utils.Constant;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.connection.ConnectInterceptor;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by TienGiang on 16/3/2017.
 */

public class BaseRetrofit {

    private static Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .create();

    private static OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(Constant.NW_REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(Constant.NW_READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(Constant.NW_WRITE_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .retryOnConnectionFailure(true)
            .build();

    public static Retrofit instance(){
        return new Retrofit.Builder()
                .baseUrl(Constant.URL_HOST)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static IServices instanceService() {
        return new Retrofit.Builder()
                .baseUrl(Constant.URL_HOST)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
                .create(IServices.class);
    }

    public static IServiceSearch instanceSearch() {
        return new Retrofit.Builder()
                .baseUrl(Constant.URL_SEARCH_HOST)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
                .create(IServiceSearch.class);
    }

}
