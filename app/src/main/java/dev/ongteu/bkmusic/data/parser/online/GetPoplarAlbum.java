package dev.ongteu.bkmusic.data.parser.online;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.List;

import dev.ongteu.bkmusic.R;
import dev.ongteu.bkmusic.data.model.AlbumItem;
import dev.ongteu.bkmusic.data.parser.api.BaseRetrofit;
import dev.ongteu.bkmusic.data.parser.api.IServices;
import dev.ongteu.bkmusic.fragment.PopularAlbumFragment;
import dev.ongteu.bkmusic.adapter.PopularAlbumRecyclerViewAdapter;
import dev.ongteu.bkmusic.utils.Constant;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

/**
 * Created by TienGiang on 16/3/2017.
 */

public class GetPoplarAlbum {

    public GetPoplarAlbum(int playlistCode, int page, final Context context, final RecyclerView recyclerView, final PopularAlbumFragment.OnListFragmentInteractionListener mListener){

        final MaterialDialog dialog = new MaterialDialog.Builder(context)
                .title(R.string.menuPopularAlbum)
                .content(R.string.waitting_text)
                .cancelable(false)
                .show();

        final IServices service = BaseRetrofit.instanceService();
        service.listAlbum(playlistCode, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<AlbumItem>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(List<AlbumItem> albumItems) {
                        PopularAlbumRecyclerViewAdapter adapter = new PopularAlbumRecyclerViewAdapter(albumItems, mListener, context);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            dialog.setContent(Constant.MSS_NETWORK_ERROR + ":" + ((HttpException) e).message());
                            dialog.setCancelable(true);
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
