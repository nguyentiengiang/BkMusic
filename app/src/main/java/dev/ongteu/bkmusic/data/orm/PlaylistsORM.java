package dev.ongteu.bkmusic.data.orm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import dev.ongteu.bkmusic.data.DatabaseWrapper;
import dev.ongteu.bkmusic.data.model.PlaylistItem;
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

    public static long addPlaylistOffline(Context context, PlaylistItem playlistItem) {
        long result = -1;

        DatabaseWrapper databaseWrapper = new DatabaseWrapper(context);
        SQLiteDatabase mDB = databaseWrapper.getWritableDatabase();

        try {
            if (mDB != null) {
                ContentValues values = convertToContentValues(playlistItem);
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

    private static ContentValues convertToContentValues(PlaylistItem playlistItem) {
        ContentValues values = new ContentValues();
        values.put(COL_ID, playlistItem.getId());
        values.put(COL_NAME, playlistItem.getName());
        return values;
    }

    private static PlaylistItem convertToPlaylistOffline(Cursor c) {
        PlaylistItem playlistItem = new PlaylistItem();
        playlistItem.setId(c.getInt(c.getColumnIndex(COL_ID)));
        playlistItem.setName(c.getString(c.getColumnIndex(COL_NAME)));
        return playlistItem;
    }

    public static PlaylistItem getPlaylistOfflineById(Context context, int id) {
        PlaylistItem playlistItem = new PlaylistItem();

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

    public static ArrayList<PlaylistItem> getListPlaylist(final Context context) {
        ArrayList<PlaylistItem> list = new ArrayList<PlaylistItem>();

        DatabaseWrapper databaseWrapper = new DatabaseWrapper(context);
        SQLiteDatabase mDB = databaseWrapper.getWritableDatabase();

        if (mDB != null) {
            Cursor c = mDB
                    .query(TABLE_NAME, null, null, null, null, null, null);
            if (c.moveToFirst()) {
                do {
                    PlaylistItem item = convertToPlaylistOffline(c);
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
