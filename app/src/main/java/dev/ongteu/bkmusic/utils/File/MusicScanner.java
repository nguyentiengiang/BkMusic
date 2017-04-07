package dev.ongteu.bkmusic.utils.File;

import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.provider.MediaStore;
import android.util.Log;

import com.sromku.simple.storage.Storage;

import java.io.File;
import java.io.IOException;
import java.util.List;

import dev.ongteu.bkmusic.data.dao.SongDAO;
import dev.ongteu.bkmusic.utils.Common;
import dev.ongteu.bkmusic.utils.Constant;

/**
 * Created by TienGiang on 19/3/2017.
 */

public class MusicScanner {

    public MusicScanner(final Context appContext) {
        Storage storage = FileHelper.initStorge(appContext);
        List<File> userFiles = storage.getNestedFiles("/");

        for (File fileItem : userFiles) {
            if (true && (fileItem.toString().toLowerCase().endsWith(".mp3")
                    || fileItem.toString().toLowerCase().endsWith(".m4a")
                    || fileItem.toString().toLowerCase().endsWith(".aac")
                    || fileItem.toString().toLowerCase().endsWith(".flac")
            )) {
                String fileName = fileItem.getName();
                SongDAO songDAO = new SongDAO(appContext, true);
                if (songDAO.isSongNotOnDb(fileName)) {
                    MediaMetadataRetriever metadataRetriever = new MediaMetadataRetriever();
                    try {
                        metadataRetriever.setDataSource(fileItem.getAbsolutePath());
//                        final String songAlbum = FileHelper.extractMetadata(metadataRetriever, MediaMetadataRetriever.METADATA_KEY_ALBUM, Constant.MUSIC_LOCAL_ALBUM);
                        String keyDuration = metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
                        // ensure the duration is a digit, otherwise return null song
                        if (keyDuration != null && keyDuration.matches("\\d+") && (Integer.parseInt(keyDuration) > Constant.MIN_SONG_LENGTH)) {
                            String songName = FileHelper.extractMetadata(metadataRetriever, MediaMetadataRetriever.METADATA_KEY_TITLE, fileItem.getName());
                            String singer = FileHelper.extractMetadata(metadataRetriever, MediaMetadataRetriever.METADATA_KEY_ARTIST, Constant.MUSIC_LOCAL_ARTIST);
                            String singerUrl = "";
                            String bgImage = "";

                            String keyMp3 = Common.generateImageName(fileName);
                            String mp3Url = Constant.URL_LOCAL_FILE + fileItem.getAbsolutePath();
                            String avatar = FileHelper.saveAlbumArt(metadataRetriever, storage, keyMp3);
                            String songUrl = "";
                            int isUserLocal = Constant.IS_USER_LOCAL;

                            songDAO.addNewSong(songName, singer, singerUrl, bgImage, avatar, keyMp3, mp3Url, songUrl, fileName, isUserLocal);
                        }
                    } catch (Exception ex) {
                        Log.e("SCAN FAIL", "export song " + fileName + " fail");
                    }
                }
            }
        }
    }

}
