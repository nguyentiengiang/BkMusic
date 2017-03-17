package dev.ongteu.bkmusic.data.parser.online;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.afollestad.materialdialogs.MaterialDialog;

import dev.ongteu.bkmusic.R;
import dev.ongteu.bkmusic.data.model.MusicChartItem;
import dev.ongteu.bkmusic.data.parser.api.BaseRetrofit;
import dev.ongteu.bkmusic.data.parser.api.IServices;
import dev.ongteu.bkmusic.fragment.MusicChartFragment;
import dev.ongteu.bkmusic.adapter.MusicChartRecyclerViewAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by TienGiang on 16/3/2017.
 */

public class GetMusicChart {

    public static MusicChartItem CHART_ITEM = new MusicChartItem();

    public GetMusicChart(final Context context, int playlistCode, final RecyclerView recyclerView, final MusicChartFragment.OnListFragmentInteractionListener mListener){
        IServices service = BaseRetrofit.instance().create(IServices.class);
        Call<MusicChartItem> call = service.singleChart(playlistCode);

        final MaterialDialog dialog = new MaterialDialog.Builder(context)
                .title(R.string.menuMusicChart)
                .content(R.string.waitting_text)
                .cancelable(false)
                .show();

        call.enqueue(new Callback<MusicChartItem>() {
            @Override
            public void onResponse(Call<MusicChartItem> call, Response<MusicChartItem> response) {
                CHART_ITEM = response.body();
                MusicChartRecyclerViewAdapter apdater = new MusicChartRecyclerViewAdapter(CHART_ITEM.getSongs(), mListener, context);
                recyclerView.setAdapter(apdater);
                dialog.dismiss();
                Log.e("music chart changed", "DATA CHANGE");
            }

            @Override
            public void onFailure(Call<MusicChartItem> call, Throwable t) {

            }
        });
    }

}
