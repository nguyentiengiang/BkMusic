package dev.ongteu.bkmusic.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.view.View;
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
     *
     * @param context
     * @param imageView
     * @param urlImage
     */
    public MyPicasso(final Context context, final ImageView imageView, final String urlImage) {
        Picasso.with(context).load(urlImage + "?sp")
                .placeholder(R.drawable.default_record_album_sm)
                .error(R.drawable.splash_music_0)
                .networkPolicy(NetworkPolicy.OFFLINE).into(imageView, new Callback() {
            @Override
            public void onSuccess() {
            }

            @Override
            public void onError() {
                Picasso.with(context).load(urlImage + "?sp")
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
     * @param viewRoot
     */
    public MyPicasso(final Context context, final ShadowImageView imageView, final String urlImage, final View viewRoot) {
        Picasso.with(context).load(urlImage + "?sp")
                .placeholder(R.drawable.default_record_album)
                .error(R.drawable.default_record_album)
                .transform(new CropCircleTransformation())
                .networkPolicy(NetworkPolicy.OFFLINE).into(imageView, new Callback() {
            @Override
            public void onSuccess() {
//                changeBackgroundPlayer(imageView, viewRoot);
            }

            @Override
            public void onError() {
                Picasso.with(context).load(urlImage + "?sp")
                        .transform(new CropCircleTransformation())
                        .into(imageView, new Callback() {
                            @Override
                            public void onSuccess() {
//                                changeBackgroundPlayer(imageView, viewRoot);
                            }

                            @Override
                            public void onError() {
                                Log.v("Picasso", "Couldn't load image player");
                            }
                        });
            }
        });
    }

    private void changeBackgroundPlayer(final ImageView imageView, final View viewRoot) {
        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmapImg = drawable.getBitmap();
        Palette.from(bitmapImg).generate(new Palette.PaletteAsyncListener() {
            public void onGenerated(Palette palette) {
                Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();
                if (vibrantSwatch != null) {
                    viewRoot.findViewById(R.id.bg_player).setBackgroundColor(vibrantSwatch.getRgb());
                }
            }
        });
    }

}
