package dev.ongteu.bkmusic.data.orm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import dev.ongteu.bkmusic.data.DatabaseWrapper;
import dev.ongteu.bkmusic.data.model.PlaylistOfflineItem;

/**
 * Created by TienGiang on 16/10/2016.
 */

public class PlaylistsORM {

    public static final String TABLE_NAME = "Playlists";

    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";

    /*
     * SQL CREATE TABLE
     */
    public static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
            + " (" + COL_ID + " INTEGER PRIMARY KEY, " + COL_NAME + " TEXT);";
    /*
     * SQL DROP TABLE
     */
    public static final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS "
            + TABLE_NAME;

	/*
	 * @Decription: Function for Playlists
	 */

    public static long addPlaylist(Context context, PlaylistOfflineItem playlistOfflineItem) {
        long result = -1;

        DatabaseWrapper databaseWrapper = new DatabaseWrapper(context);
        SQLiteDatabase mDB = databaseWrapper.getWritableDatabase();

        try {
            if (mDB != null) {
                ContentValues values = convertToContentValues(playlistOfflineItem);
                result = mDB.insertOrThrow(TABLE_NAME, null, values);
            }
        } catch (Exception e) {
            Log.e("SQL_ADD_Category =>", "Failed: " + e.getMessage());
        } finally {
            if (mDB != null) {
                mDB.close();
            }
        }

        return result;
    }

    private static ContentValues convertToContentValues(PlaylistOfflineItem playlistOfflineItem) {
        ContentValues values = new ContentValues();
        values.put(COL_ID, playlistOfflineItem.getId());
        values.put(COL_NAME, playlistOfflineItem.getName());
        return values;
    }

    private static PlaylistOfflineItem convertToPlaylistOffline(Cursor c) {
        PlaylistOfflineItem playlistItem = new PlaylistOfflineItem();
        playlistItem.setId(c.getInt(c.getColumnIndex(COL_ID)));
        playlistItem.setName(c.getString(c.getColumnIndex(COL_NAME)));
        return playlistItem;
    }

    public static PlaylistOfflineItem getPlaylistOfflineById(Context context, int id) {
        PlaylistOfflineItem playlistItem = new PlaylistOfflineItem();

        DatabaseWrapper databaseWrapper = new DatabaseWrapper(context);
        SQLiteDatabase mDB = databaseWrapper.getWritableDatabase();

        if (mDB != null) {
            Cursor c = mDB.rawQuery("SELECT * FROM " + TABLE_NAME
                    + " WHERE " + COL_ID + " = " + id, null);
            c.moveToFirst();
            playlistItem = convertToPlaylistOffline(c);
            mDB.close();
        }
        return playlistItem;
    }

    public static ArrayList<PlaylistOfflineItem> getListPlaylistOffline(final Context context) {
        ArrayList<PlaylistOfflineItem> list = new ArrayList<PlaylistOfflineItem>();

        DatabaseWrapper databaseWrapper = new DatabaseWrapper(context);
        SQLiteDatabase mDB = databaseWrapper.getWritableDatabase();

        if (mDB != null) {
            Cursor c = mDB
                    .query(TABLE_NAME, null, null, null, null, null, null);
            if (c.moveToFirst()) {
                do {
                    PlaylistOfflineItem item = convertToPlaylistOffline(c);
                    list.add(item);
                } while (c.moveToNext());
            }
            mDB.close();
        }
        return list;
    }

    public static boolean isEmpty(final Context context){
        boolean isEmpty = true;
        DatabaseWrapper databaseWrapper = new DatabaseWrapper(context);
        SQLiteDatabase mDB = databaseWrapper.getWritableDatabase();
        if (mDB != null) {
            int numberRecords = mDB.query(TABLE_NAME, null, null, null, null, null, null).getCount();
            isEmpty = (numberRecords > 0) ? false : true;
        }
        return isEmpty;
    }

}
