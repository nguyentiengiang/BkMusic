package dev.ongteu.bkmusic.utils.File;

import android.content.Context;
import android.media.MediaMetadataRetriever;

import com.orm.query.Condition;
import com.orm.query.Select;
import com.sromku.simple.storage.Storage;

import java.io.File;
import java.util.List;

import dev.ongteu.bkmusic.data.entity.Songs;
import dev.ongteu.bkmusic.utils.Common;
import dev.ongteu.bkmusic.utils.Constant;

/**
 * Created by TienGiang on 19/3/2017.
 */

public class MusicScanner {

    private Context mContext;

    public MusicScanner(final Context appContext) {
        mContext = appContext;
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
                String bgimage = "";
                String fileName = fileItem.getName();
                String keyMp3 = Common.generateImageName(fileName);
                String mp3Url = fileItem.getAbsolutePath();
                String avatar = Common.getDirectoryOfFile(mp3Url, fileName) + Constant.PATH_MUSIC_USER_ART + "/" + keyMp3 + Constant.IMG_EXT;
                String songUrl = "";
                int isUserLocal = Constant.IS_USER_LOCAL;
                FileHelper.saveAlbumArt(metadataRetriever, storage, keyMp3);
                if (Select.from(Songs.class).where(Condition.prop("KEY_MP3").eq(keyMp3), Condition.prop("IS_USER_LOCAL").eq(Constant.IS_USER_LOCAL)).count() == 0){
                    Songs songItem = new Songs(songName, singer, singerUrl, bgimage, avatar, keyMp3, mp3Url, songUrl, fileName, isUserLocal);
                    songItem.save();
                }
            }
        }
    }

}
