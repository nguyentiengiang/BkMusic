package dev.ongteu.bkmusic.utils.File;

import android.content.Context;
import android.media.MediaMetadataRetriever;

import com.sromku.simple.storage.Storage;

import java.io.File;
import java.util.List;

import dev.ongteu.bkmusic.data.dao.SongDAO;
import dev.ongteu.bkmusic.utils.Common;
import dev.ongteu.bkmusic.utils.Constant;

/**
 * Created by TienGiang on 19/3/2017.
 */

public class MusicScanner {

//    private Context mContext;

    public MusicScanner(final Context appContext) {
//        this.mContext = appContext;
        Storage storage = FileHelper.initStorge(appContext);

        List<File> userFiles = storage.getNestedFiles(FileHelper.mUserMusicPath);

        for (File fileItem : userFiles) {
            if (fileItem.isFile() && (fileItem.toString().toLowerCase().endsWith(".mp3")
                    || fileItem.toString().toLowerCase().endsWith(".m4a") || fileItem.toString().toLowerCase().endsWith(".aac")
                    || fileItem.toString().toLowerCase().endsWith(".flac")
            )) {
                MediaMetadataRetriever metadataRetriever = new MediaMetadataRetriever();
                metadataRetriever.setDataSource(fileItem.getAbsolutePath());

                String songName = FileHelper.extractMetadata(metadataRetriever, MediaMetadataRetriever.METADATA_KEY_TITLE, fileItem.getName());
                String singer = FileHelper.extractMetadata(metadataRetriever, MediaMetadataRetriever.METADATA_KEY_ARTIST, Constant.MUSIC_LOCAL_ARTIST);
//            final String songAlbum = FileHelper.extractMetadata(metadataRetriever, MediaMetadataRetriever.METADATA_KEY_ALBUM, Constant.MUSIC_LOCAL_ALBUM);
//            final int duration;
//            String keyDuration = metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
//            // ensure the duration is a digit, otherwise return null song
//            if (keyDuration == null || !keyDuration.matches("\\d+")) return null;
//            duration = Integer.parseInt(keyDuration);

                String singerUrl = "";
                String bgImage = "";
                String fileName = fileItem.getName();
                String keyMp3 = Common.generateImageName(fileName);
                String mp3Url = Constant.URL_LOCAL_FILE + fileItem.getAbsolutePath();
                String avatar = Constant.URL_LOCAL_FILE + Common.getDirectoryOfFile(mp3Url, fileName) + Constant.PATH_MUSIC_USER_ART + "/" + keyMp3 + Constant.IMG_EXT;
                String songUrl = "";
                int isUserLocal = Constant.IS_USER_LOCAL;

                SongDAO songDAO = new SongDAO(appContext, true);
                if (songDAO.isSongNotOnDb(keyMp3)) {
                    FileHelper.saveAlbumArt(metadataRetriever, storage, keyMp3);
                    songDAO.addNewSong(songName, singer, singerUrl, bgImage, avatar, keyMp3, mp3Url, songUrl, fileName, isUserLocal);
                }
            }
        }
    }

}
