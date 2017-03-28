package dev.ongteu.bkmusic.data.parser.api;

import dev.ongteu.bkmusic.data.model.search.SearchNCT;
import dev.ongteu.bkmusic.utils.Constant;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by TienGiang on 28/3/2017.
 */

public interface IServiceSearch {

    @GET(Constant.URL_SEARCH_QUERRY+"{input}")
    Observable<SearchNCT> searchResult(@Path("input") String inputString);

}
