package dev.ongteu.bkmusic.utils;

/**
 * Created by TienGiang on 18/3/2017.
 */

public class Common {
    public static String cutterLongName(String strOrigin){
        return (strOrigin.length() < Constant.MAX_LENGTH_NAME_TITLE) ? strOrigin : strOrigin.substring(0, Math.min(strOrigin.length(), Constant.MAX_LENGTH_NAME_TITLE)) + "...";
    }

    public static int safeLongToInt(long l) {
        return (int) Math.max(Math.min(Integer.MAX_VALUE, l), Integer.MIN_VALUE);
    }

}
