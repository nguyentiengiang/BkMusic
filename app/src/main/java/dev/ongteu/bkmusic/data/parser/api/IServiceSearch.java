package dev.ongteu.bkmusic.data.parser.api;

import dev.ongteu.bkmusic.data.model.search.SearchNCT;
import dev.ongteu.bkmusic.utils.Constant;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by TienGiang on 28/3/2017.
 */

public interface IServiceSearch {

    @GET(Constant.URL_SEARCH_QUERRY)
    Call<SearchNCT> searchResult(@Query("q") String inputString);

}
