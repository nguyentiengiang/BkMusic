package dev.ongteu.bkmusic.utils;

import android.content.Context;
import android.content.DialogInterface;

import com.afollestad.materialdialogs.MaterialDialog;

import dev.ongteu.bkmusic.R;
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
		if (Connectivity.isNetworkAvailable(context)) {
			new CategoryDAO(context, 0, null);
		} else {
			MyDialog.getDialogNoInternet(context);
		}

//        final MaterialDialog dialog = new MaterialDialog.Builder(context)
//                .title(R.string.scan_music)
//                .content(R.string.waitting_text)
//                .cancelable(false)
//                .show();

		new MusicScanner(context);

	}

	public boolean isFirstRun() {
		return isFirstRun;
	}
	

}
