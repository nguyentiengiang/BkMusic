package dev.ongteu.bkmusic.data.dao;

import android.content.Context;
import android.util.Log;

import com.github.mohammad.songig.common.PlayMode;
import com.github.mohammad.songig.model.Song;
import com.orm.query.Select;
import com.sromku.simple.storage.Storage;

import java.util.ArrayList;
import java.util.List;

import dev.ongteu.bkmusic.data.entity.Songs;
import dev.ongteu.bkmusic.utils.Common;
import dev.ongteu.bkmusic.utils.Constant;
import dev.ongteu.bkmusic.utils.File.FileHelper;

/**
 * Created by TienGiang on 17/3/2017.
 */

public class SongDAO {

    public SongDAO(){
    }

    public List<Song> getUserSong(final Context context){
        List<Song> songIgList = new ArrayList<Song>();
        List<Songs> songList = Select.from(Songs.class).list();
        Storage storage = FileHelper.initStorge(context);
        for (Songs song : songList) {
            if (storage.isFileExist(Constant.PATH_MUSIC_USER, song.getFileName()) || storage.isFileExist(Constant.PATH_MUSIC_APP, song.getFileName())) {
//                if (song.getIsUserLocal() == Constant.IS_USER_LOCAL) {
//                    if (!storage.isFileExist(Constant.PATH_MUSIC_USER + "/" + Constant.PATH_MUSIC_USER_ART, Common.generateImageName(song.getFileName()) + ".jpg"))
//                        FileHelper.saveAlbumArt(context, song.getMp3Url(), song.getFileName());
//                }

                int songId = Common.safeLongToInt(song.getId());
                String songName = song.getSongName();
                String singer = song.getSinger();
                String albumName = "";
                String url = song.getMp3Url();
                String imageUrl = Constant.URL_LOCAL_FILE + song.getAvatar();
                String albumId = song.getFileName();

                Song songIgItem = new Song(songId, songName, singer, albumName, url, imageUrl, albumId, PlayMode.LOCAL);
                songIgList.add(songIgItem);
            } else {
                Songs delSong = Songs.findById(Songs.class, song.getId());
                delSong.delete();
            }
        }
        return songIgList;
    }

}
