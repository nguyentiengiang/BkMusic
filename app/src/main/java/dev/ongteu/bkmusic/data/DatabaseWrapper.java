package dev.ongteu.bkmusic.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import dev.ongteu.bkmusic.data.orm.CategoriesORM;
import dev.ongteu.bkmusic.utils.Constant;

/**
 * Created by TienGiang on 19/9/2016.
 */
public class DatabaseWrapper extends SQLiteOpenHelper {

    public DatabaseWrapper(Context context) {
        super(context, Constant.DATABASE_NAME, null, Constant.DATABASE_VERSION);
//        Log.i("DW_CREATED", "DatabaseWapper was create");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        Log.i("DB_TAG", "CREATE SUCCESS");
        db.execSQL(CategoriesORM.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("LOG_TAG", "Upgrading database from version " + oldVersion
                + " to " + newVersion + ", which will destroy all old data");
        db.execSQL(CategoriesORM.SQL_DROP_TABLE);
        onCreate(db);
    }
}
