package dev.ongteu.bkmusic.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import dev.ongteu.bkmusic.R;
import dev.ongteu.bkmusic.data.dao.CategoryDAO;
import dev.ongteu.bkmusic.ui.ShadowImageView;
import dev.ongteu.bkmusic.utils.File.MusicScanner;
import dev.ongteu.bkmusic.utils.MyDialog;
import dev.ongteu.bkmusic.utils.MySPManager;
import dev.ongteu.bkmusic.utils.network.Connectivity;

public class SplashScreen extends Activity {

    private int SPLASH_TIME_OUT = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ((ShadowImageView) findViewById(R.id.imgLoading)).startRotateAnimation();

        final Context mContext = this;
        MySPManager spManager = new MySPManager(mContext);
        if (spManager.isFirstRun()) {
            new Thread(new Runnable() {
                public void run() {
                    new MusicScanner(mContext);
                    if (Connectivity.isNetworkAvailable(getBaseContext())) {
                        new CategoryDAO(mContext);
                    } else {
                        MyDialog.getDialogNoInternet(mContext);
                    }
                }
            }).start();
            SPLASH_TIME_OUT = 15000;
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);

    }
}
