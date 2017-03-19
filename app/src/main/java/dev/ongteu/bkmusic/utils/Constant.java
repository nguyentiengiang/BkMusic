package dev.ongteu.bkmusic.utils;

/**
 * Created by TienGiang on 19/9/2016.
 */
public class Constant {
    /**
     * Shared Preference Setting
     */
    public static final String SHARE_PREF = "bkmusic_spref";
    public static final String SP_KEY_FIRST_RUN = "isFirstRun";

    /**
     * API URLs
     */
    public static final String URL_HOST = "http://ec2-54-238-181-147.ap-northeast-1.compute.amazonaws.com/s10/api/BkMusic/";
    public static final String URL_GET_CATEGORY = "GetCategories";
    public static final String URL_GET_NHACHOT = "GetNhacHot";
    public static final String URL_GET_POPULAR_ALBUM = "GetAlbum";
    public static final String URL_GET_CHART = "GetChart";
    public static final String URL_GET_SONGS = "GetSongs";
    public static final String URL_GET_SINGER = URL_HOST + "GetSinger?urlSinger=";

    /**
     * Category Parent ID
     */
    public static final int PARENT_NHACHOT = 1;
    public static final int PARENT_POPALBUM = 2;
    public static final int PARENT_CHART = 3;

    /**
     * Category Column Number
     */
    public static final int COLUMN_COUNT_2 = 2;
    public static final int COLUMN_COUNT_3 = 3;

    /**
     * Define other constants here ...
     */
    public static final int MAX_LENGTH_NAME_TITLE = 32;


}
