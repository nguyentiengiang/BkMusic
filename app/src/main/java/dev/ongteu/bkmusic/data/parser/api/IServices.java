package dev.ongteu.bkmusic.data.parser.api;

import java.util.List;

import dev.ongteu.bkmusic.data.model.AlbumItem;
import dev.ongteu.bkmusic.data.model.CategoryItem;
import dev.ongteu.bkmusic.data.model.HotSongItem;
import dev.ongteu.bkmusic.data.model.MusicChartItem;
import dev.ongteu.bkmusic.data.model.SongItem;
import dev.ongteu.bkmusic.utils.Constant;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by TienGiang on 17/3/2017.
 */

public interface IServices {

    @GET(Constant.URL_GET_CATEGORY)
    Call<List<CategoryItem>> listCategory();

    @GET(Constant.URL_GET_NHACHOT+"/{categoryCode}/{page}")
    Observable<List<HotSongItem>> listHotSongs(@Path("categoryCode") int categoryCode, @Path("page") int page);

    @GET(Constant.URL_GET_POPULAR_ALBUM+"/{categoryCode}/{page}")
    Observable<List<AlbumItem>> listAlbum(@Path("categoryCode") int categoryCode, @Path("page") int page);

    @GET(Constant.URL_GET_CHART+"/{categoryCode}")
    Observable<MusicChartItem> singleChart(@Path("categoryCode") int categoryCode);

    @GET(Constant.URL_GET_SONGS)
    Call<List<SongItem>> listSongs(@Query("urlSong") String urlSong);

}
