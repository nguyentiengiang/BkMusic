package dev.ongteu.bkmusic.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

public class MyDialog {

	public static void getDialogNoInternet(Context context) {

		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("Sorry")
				.setMessage("Please check your internet connection!")
				.setCancelable(false);

		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				System.exit(0);
			}
		});
		AlertDialog alert = builder.create();
		alert.show();

	}

	public static void getDialogExit(Context context) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("Exit Application");
		builder.setMessage("Are yout want to close this application?");
		builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				Log.e("info", "NO");
				return;
			}
		});
		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				Log.e("info", "YES");
				System.exit(0);
			}
		});
		builder.show();
	}
		
	public static void getDialogNotRun(Context context) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("Exit Application");
		builder.setMessage("Can't run application right now :( Sorry about that!");
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				Log.e("info", "OK");
				System.exit(0);
			}
		});
		builder.show();
	}
	
}
