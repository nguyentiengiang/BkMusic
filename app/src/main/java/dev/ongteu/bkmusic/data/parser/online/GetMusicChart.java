package dev.ongteu.bkmusic.data.parser.online;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.afollestad.materialdialogs.MaterialDialog;

import dev.ongteu.bkmusic.R;
import dev.ongteu.bkmusic.data.model.MusicChartItem;
import dev.ongteu.bkmusic.data.parser.api.BaseRetrofit;
import dev.ongteu.bkmusic.data.parser.api.IServices;
import dev.ongteu.bkmusic.fragment.MusicChartFragment;
import dev.ongteu.bkmusic.adapter.MusicChartRecyclerViewAdapter;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by TienGiang on 16/3/2017.
 */

public class GetMusicChart {

    public GetMusicChart(int playlistCode, final Context context, final RecyclerView recyclerView, final MusicChartFragment.OnListFragmentInteractionListener mListener){

        final MaterialDialog dialog = new MaterialDialog.Builder(context)
                .title(R.string.menuMusicChart)
                .content(R.string.waitting_text)
                .cancelable(false)
                .show();

        final IServices service = BaseRetrofit.instanceService();
        service.singleChart(playlistCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MusicChartItem>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(MusicChartItem musicChartItem) {
                        MusicChartRecyclerViewAdapter adapter = new MusicChartRecyclerViewAdapter(musicChartItem.getSongs(), mListener, context);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        recyclerView.setBackgroundResource(R.drawable.network_error);
                        dialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
