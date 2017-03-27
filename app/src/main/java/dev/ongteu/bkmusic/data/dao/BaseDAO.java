package dev.ongteu.bkmusic.data.dao;

import android.content.Context;

import dev.ongteu.bkmusic.data.entity.OrmaDatabase;

/**
 * Created by TienGiang on 26/3/2017.
 */

public class BaseDAO {

    protected Context context = null;
    protected OrmaDatabase bkOrm = null;

    public BaseDAO(final Context context, boolean isOnMainThread) {
        this.context = context;
        this.bkOrm = isOnMainThread ? BkOrm.builderOm(this.context) : BkOrm.builderBg(this.context);
    }

}
