package dev.ongteu.bkmusic.data.parser.online;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.List;

import dev.ongteu.bkmusic.R;
import dev.ongteu.bkmusic.data.model.AlbumItem;
import dev.ongteu.bkmusic.data.parser.api.BaseRetrofit;
import dev.ongteu.bkmusic.data.parser.api.IServices;
import dev.ongteu.bkmusic.fragment.PopularAlbumFragment;
import dev.ongteu.bkmusic.adapter.PopularAlbumRecyclerViewAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by TienGiang on 16/3/2017.
 */

public class GetPoplarAlbum {

    public static List<AlbumItem> POPULAR_ALBUM_ITEMS = new ArrayList<AlbumItem>();

    public GetPoplarAlbum(final Context context, int playlistCode, int page, final RecyclerView recyclerView, final PopularAlbumFragment.OnListFragmentInteractionListener mListener){
        IServices service = BaseRetrofit.instance().create(IServices.class);
        Call<List<AlbumItem>> call = service.listAlbum(playlistCode, page);

        final MaterialDialog dialog = new MaterialDialog.Builder(context)
                .title(R.string.menuPopularAlbum)
                .content(R.string.waitting_text)
                .cancelable(false)
                .show();

        call.enqueue(new Callback<List<AlbumItem>>() {
            @Override
            public void onResponse(Call<List<AlbumItem>> call, Response<List<AlbumItem>> response) {
                POPULAR_ALBUM_ITEMS = response.body();
                PopularAlbumRecyclerViewAdapter apdater = new PopularAlbumRecyclerViewAdapter(POPULAR_ALBUM_ITEMS, mListener, context);
                recyclerView.setAdapter(apdater);
                dialog.dismiss();
                Log.e("popular data changed", "DATA CHANGE");
            }

            @Override
            public void onFailure(Call<List<AlbumItem>> call, Throwable t) {

            }
        });
    }

}
