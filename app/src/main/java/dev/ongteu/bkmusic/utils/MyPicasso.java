package dev.ongteu.bkmusic.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import dev.ongteu.bkmusic.R;
import dev.ongteu.bkmusic.ui.ShadowImageView;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

/**
 * Created by TienGiang on 18/3/2017.
 */

public class MyPicasso {

    /**
     * Using image view default
     * @param context
     * @param imageView
     * @param urlImage
     */
    public MyPicasso(final Context context, final ImageView imageView, final String urlImage){
        Picasso.with(context).load(urlImage)
                .placeholder(R.drawable.lol_guy)
                .error(R.drawable.lol_guy)
                .networkPolicy(NetworkPolicy.OFFLINE).into(imageView, new Callback() {
            @Override
            public void onSuccess() {
            }

            @Override
            public void onError() {
                Picasso.with(context).load(urlImage)
                        .into(imageView, new Callback() {
                            @Override
                            public void onSuccess() {
                            }

                            @Override
                            public void onError() {
                                Log.v("Picasso", "Couldn't load image");
                            }
                        });
            }
        });
    }

    /**
     * Using for load player image
     *
     * @param context
     * @param imageView
     * @param urlImage
     * @param flag is for android >= 5
     */
    public MyPicasso(final Context context, final ShadowImageView imageView, final String urlImage, boolean flag){
        Picasso.with(context).load(urlImage)
                .placeholder(R.drawable.lol_guy)
                .error(R.drawable.lol_guy)
                .transform(new CropCircleTransformation())
                .networkPolicy(NetworkPolicy.OFFLINE).into(imageView, new Callback() {
            @Override
            public void onSuccess() {
            }

            @Override
            public void onError() {
                Picasso.with(context).load(urlImage)
                        .into(imageView, new Callback() {
                            @Override
                            public void onSuccess() {
                            }

                            @Override
                            public void onError() {
                                Log.v("Picasso", "Couldn't load image player");
                            }
                        });
            }
        });
    }
}
