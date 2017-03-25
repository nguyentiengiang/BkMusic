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

    public static final String DB_NAME = "appbkmusic.db";
    public static final int DB_VERSION = 1;

    /**
     * Local music path of App
     */
    public static final String PATH_MUSIC_APP = "BkMusic";
    public static final String PATH_MUSIC_APP_ART = "art";
    public static final String PATH_MUSIC_USER = "Music";
    public static final String PATH_MUSIC_USER_ART = "art";

    /**
     * Protocol file
     */
    public static final String URL_LOCAL_FILE = "file://";
    public static final String IMG_EXT = ".png";

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
    public static final int MAX_LENGTH_NAME_TITLE_CATE = 20;

    public static final int PLAY_TYPE_UNKNOW = 0;
    public static final int PLAY_TYPE_ONLINE = 1;
    public static final int PLAY_TYPE_OFFLINE = 2;

    /**
     *
     */
    public static final int IS_USER_LOCAL = 1;
    public static final String MUSIC_LOCAL_ARTIST = "Unknown Artist";
    public static final String MUSIC_LOCAL_ALBUM = "Unknown Album";

    /**
     * Common message
     */
    public static final String MSS_NETWORK_ERROR = "Network unstable";


}
