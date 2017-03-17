package dev.ongteu.bkmusic.utils;

import android.content.Context;

import dev.ongteu.bkmusic.data.dao.CategoryDAO;
import dev.ongteu.bkmusic.utils.network.Connectivity;

public class Loader {

	private final Context context;
	private boolean isFirstRun = false;
	
	public Loader(Context context) {
		super();
		this.context = context;

		MySPManager spManager = new MySPManager(context);
		this.isFirstRun = spManager.isFirstRun();
		if (Connectivity.isNetworkAvailable(context)) {
			new CategoryDAO(context, 0, null);
		} else {
			MyDialog.getDialogNoInternet(context);
		}

	}

	public boolean isFirstRun() {
		return isFirstRun;
	}
	

}
