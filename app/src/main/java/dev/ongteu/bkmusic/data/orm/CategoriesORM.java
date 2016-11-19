package dev.ongteu.bkmusic.data.orm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import dev.ongteu.bkmusic.data.DatabaseWrapper;
import dev.ongteu.bkmusic.data.model.CategoryItem;

/**
 * Created by TienGiang on 26/9/2016.
 */

public class CategoriesORM {

    public static final String TABLE_NAME = "Categories";

    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_IMG = "image";
    public static final String COL_PARENT_ID = "parent_id";

    /*
     * SQL CREATE TABLE
     */
    public static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
            + " (" + COL_ID + " INTEGER PRIMARY KEY, " + COL_NAME + " TEXT," + COL_IMG + " TEXT," + COL_PARENT_ID + " INTEGER);";
    /*
     * SQL DROP TABLE
     */
    public static final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS "
            + TABLE_NAME;

	/*
	 * @Decription: Function for Category
	 */

    public static long addCategory(Context context, CategoryItem category) {
        long result = -1;

        DatabaseWrapper databaseWrapper = new DatabaseWrapper(context);
        SQLiteDatabase mDB = databaseWrapper.getWritableDatabase();

        try {
            if (mDB != null) {
                ContentValues values = convertToContentValues(category);
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

    private static ContentValues convertToContentValues(CategoryItem category) {
        ContentValues values = new ContentValues();
        values.put(COL_ID, category.getId());
        values.put(COL_NAME, category.getName());
        values.put(COL_IMG, category.getImg());
        values.put(COL_PARENT_ID, category.getParent_id());
        return values;
    }

    private static CategoryItem convertToCategory(Cursor c) {
        CategoryItem cate = new CategoryItem();
        cate.setId(c.getInt(c.getColumnIndex(COL_ID)));
        cate.setName(c.getString(c.getColumnIndex(COL_NAME)));
        cate.setImg(c.getString(c.getColumnIndex(COL_IMG)));
        cate.setParent_id(c.getInt(c.getColumnIndex(COL_PARENT_ID)));
        return cate;
    }

    public static CategoryItem getCategoryById(Context context, int id) {
        CategoryItem cate = new CategoryItem();

        DatabaseWrapper databaseWrapper = new DatabaseWrapper(context);
        SQLiteDatabase mDB = databaseWrapper.getWritableDatabase();

        if (mDB != null) {
            Cursor c = mDB.rawQuery("SELECT * FROM " + TABLE_NAME
                    + " WHERE " + COL_ID + " = " + id, null);
            c.moveToFirst();
            cate = convertToCategory(c);
            mDB.close();
        }
        return cate;
    }

    public static ArrayList<CategoryItem> getCategoriesByParentId(Context context, int parent_id) {
        ArrayList<CategoryItem> list = new ArrayList<CategoryItem>();

        DatabaseWrapper databaseWrapper = new DatabaseWrapper(context);
        SQLiteDatabase mDB = databaseWrapper.getWritableDatabase();

        if (mDB != null) {
            Cursor c = mDB.rawQuery("SELECT * FROM " + TABLE_NAME
                    + " WHERE " + COL_PARENT_ID + " = " + parent_id, null);
            if(c.moveToFirst()) {
                do {
                    CategoryItem cate = convertToCategory(c);
                    list.add(cate);
                } while (c.moveToNext());
            }
            mDB.close();
        }
        return list;
    }

    public static ArrayList<CategoryItem> getListCategory(final Context context) {
        ArrayList<CategoryItem> list = new ArrayList<CategoryItem>();

        DatabaseWrapper databaseWrapper = new DatabaseWrapper(context);
        SQLiteDatabase mDB = databaseWrapper.getWritableDatabase();

        if (mDB != null) {
            Cursor c = mDB
                    .query(TABLE_NAME, null, null, null, null, null, null);
            if (c.moveToFirst()) {
                do {
                    CategoryItem category = convertToCategory(c);
                    list.add(category);
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
