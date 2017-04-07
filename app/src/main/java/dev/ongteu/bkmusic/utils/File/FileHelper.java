package dev.ongteu.bkmusic.utils.File;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.text.TextUtils;
import android.util.Log;

import com.sromku.simple.storage.SimpleStorage;
import com.sromku.simple.storage.Storage;

import java.io.File;
import java.io.FileFilter;

import dev.ongteu.bkmusic.utils.Common;
import dev.ongteu.bkmusic.utils.Constant;

/**
 * Created by TienGiang on 19/3/2017.
 */

public class FileHelper {

    public static String mUserMusicPath = Constant.PATH_MUSIC_USER;
    public static String mAppMusicPath = Constant.PATH_MUSIC_APP;

    public static Storage initStorge(final Context appContext){
        Storage storage = null;
        if (SimpleStorage.isExternalStorageWritable()) {
            storage = SimpleStorage.getExternalStorage();
        } else {
            storage = SimpleStorage.getInternalStorage(appContext);
        }
        if (!storage.isDirectoryExists(mUserMusicPath)) {
            storage.createDirectory(mUserMusicPath);
        }
        storage.createDirectory(mUserMusicPath + "/" + Constant.PATH_MUSIC_ART);
        storage.createDirectory(mAppMusicPath);
        storage.createDirectory(mAppMusicPath + "/" + Constant.PATH_MUSIC_ART);
        return storage;
    }

    public static String extractMetadata(MediaMetadataRetriever retriever, int key, String defaultValue) {
        String value = retriever.extractMetadata(key);
        if (TextUtils.isEmpty(value)) {
            value = defaultValue;
        }
        return value;
    }

    public static String saveAlbumArt(MediaMetadataRetriever metadataRetriever, Storage storage, String fileImgeName){
        byte[] albumArtImage = metadataRetriever.getEmbeddedPicture();
        String urlAlbumArt = "";
        if (albumArtImage != null) {
            if (!storage.isFileExist(mAppMusicPath + "/" + Constant.PATH_MUSIC_ART, fileImgeName + Constant.IMG_EXT))
                storage.createFile(mAppMusicPath + "/" + Constant.PATH_MUSIC_ART,
                        fileImgeName + Constant.IMG_EXT,
                        Bitmap.createScaledBitmap(BitmapFactory.decodeByteArray(albumArtImage, 0, albumArtImage.length), Constant.IMG_COVER_SIZE, Constant.IMG_COVER_SIZE, false)
                );
            urlAlbumArt = Constant.URL_LOCAL_FILE + storage.getFile(mAppMusicPath + "/" + Constant.PATH_MUSIC_ART, fileImgeName + Constant.IMG_EXT).getAbsolutePath();
            Log.e("EXTRACTED", "ALBUM ART DONE AT " + urlAlbumArt);
        }
        return urlAlbumArt;
    }

    public static boolean deleteMusicFile(final Context context, int pathType, String fileName, String keyMp3){
        String pathDelete = (pathType == Constant.IS_USER_LOCAL) ? mUserMusicPath : mAppMusicPath;
        String pathArtDelete = pathDelete + Constant.PATH_MUSIC_ART;
        initStorge(context).deleteFile(pathArtDelete, keyMp3 + Constant.IMG_EXT);
        return initStorge(context).deleteFile(pathDelete, fileName);
    }

    public static boolean isFileExistOnAppPath(final Context appContext, String fileName){
        return initStorge(appContext).isFileExist(mAppMusicPath, fileName);
    }

    public static class AudioFileFilter implements FileFilter {

        protected static final String TAG = "AudioFileFilter";
        /**
         * allows Directories
         */
        private final boolean allowDirectories;

        public AudioFileFilter(boolean allowDirectories) {
            this.allowDirectories = allowDirectories;
        }

        public AudioFileFilter() {
            this(true);
        }

        @Override
        public boolean accept(File f) {
            if (f.isHidden() || !f.canRead()) {
                return false;
            }

            if (f.isDirectory()) {
                return allowDirectories;
            }
            String ext = getFileExtension(f);
            if (ext == null) return false;
            try {
                if (SupportedFileFormat.valueOf(ext.toUpperCase()) != null) {
                    return true;
                }
            } catch (IllegalArgumentException e) {
                //Not known enum value
                return false;
            }
            return false;
        }

        public String getFileExtension(File f) {
            int i = f.getName().lastIndexOf('.');
            if (i > 0) {
                return f.getName().substring(i + 1);
            } else
                return null;
        }

        /**
         * Files formats currently supported by Library
         */
        public enum SupportedFileFormat {
            _3GP("3gp"),
            MP4("mp4"),
            M4A("m4a"),
            AAC("aac"),
            TS("ts"),
            FLAC("flac"),
            MP3("mp3"),
            MID("mid"),
            XMF("xmf"),
            MXMF("mxmf"),
            RTTTL("rtttl"),
            RTX("rtx"),
            OTA("ota"),
            IMY("imy"),
            OGG("ogg"),
            MKV("mkv"),
            WAV("wav");

            private String filesuffix;

            SupportedFileFormat(String filesuffix) {
                this.filesuffix = filesuffix;
            }

            public String getFilesuffix() {
                return filesuffix;
            }
        }

    }

}
