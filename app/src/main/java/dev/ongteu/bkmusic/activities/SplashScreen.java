package dev.ongteu.bkmusic.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import dev.ongteu.bkmusic.R;
import dev.ongteu.bkmusic.ui.ShadowImageView;
import dev.ongteu.bkmusic.utils.Loader;

public class SplashScreen extends Activity {

    private int SPLASH_TIME_OUT = 2500;
    ShadowImageView imgLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        imgLoading = (ShadowImageView) findViewById(R.id.imgLoading);
        imgLoading.startRotateAnimation();

        Loader loader = new Loader(this);

        if (loader.isFirstRun()) {
            SPLASH_TIME_OUT = 10000;
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
