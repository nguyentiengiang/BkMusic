package dev.ongteu.bkmusic.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by TienGiang on 19/9/2016.
 */
public class MySPManager {

    private SharedPreferences mySharePreferences;

    public MySPManager(Context context) {
        this.mySharePreferences = context.getSharedPreferences(Constant.SHARE_PREF, Context.MODE_PRIVATE);
        if (!mySharePreferences.contains(Constant.SP_KEY_FIRST_RUN)){
            setFirstRun(true);
        }
    }

    public void setFirstRun(boolean isFirstTime){
        mySharePreferences.edit().putBoolean(Constant.SP_KEY_FIRST_RUN, isFirstTime).commit();
    }

    public boolean isFirstRun(){
        return mySharePreferences.getBoolean(Constant.SP_KEY_FIRST_RUN, false);
    }

}
