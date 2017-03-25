package dev.ongteu.bkmusic.data.dao;

import android.content.Context;

import com.github.gfx.android.orma.AccessThreadConstraint;

import dev.ongteu.bkmusic.data.entity.OrmaDatabase;
import dev.ongteu.bkmusic.utils.Constant;

/**
 * Created by TienGiang on 25/3/2017.
 */

public class BkOrm {

    /**
     * Get builder on main thread
     *
     * @param context
     * @return
     */
    public static OrmaDatabase builderOm(Context context) {
        return OrmaDatabase.builder(context)
                .name(Constant.DB_NAME)
                .writeOnMainThread(AccessThreadConstraint.NONE)
                .trace(true)
                .build();
    }

    /**
     * Get builder on working on background thread
     *
     * @param context
     * @return
     */
    public static OrmaDatabase builderBg(Context context) {
        return OrmaDatabase.builder(context)
                .name(Constant.DB_NAME)
                .trace(true)
                .build();
    }

}
