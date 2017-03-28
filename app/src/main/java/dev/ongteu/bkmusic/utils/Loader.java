package dev.ongteu.bkmusic.utils;

import android.content.Context;

import dev.ongteu.bkmusic.data.dao.CategoryDAO;
import dev.ongteu.bkmusic.utils.File.MusicScanner;
import dev.ongteu.bkmusic.utils.network.Connectivity;

public class Loader {

    private final Context context;
    private boolean isFirstRun = false;

    public Loader(final Context context) {
        super();
        this.context = context;

        MySPManager spManager = new MySPManager(context);
        this.isFirstRun = spManager.isFirstRun();
        if (spManager.isFirstRun()) {
            new MusicScanner(context);
            if (Connectivity.isNetworkAvailable(context)) {
                new CategoryDAO(context);
            } else {
                MyDialog.getDialogNoInternet(context);
            }
        }

    }

}
