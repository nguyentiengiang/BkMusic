package dev.ongteu.bkmusic.utils;

import android.content.Context;
import android.widget.Toast;

import com.tonyodev.fetch.Fetch;
import com.tonyodev.fetch.listener.FetchListener;
import com.tonyodev.fetch.request.Request;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import dev.ongteu.bkmusic.data.dao.SongDAO;
import dev.ongteu.bkmusic.data.model.SongItem;
import dev.ongteu.bkmusic.utils.File.FileHelper;

/**
 * Created by TienGiang on 29/3/2017.
 */

public class MyDownloader {
    private Context mContext;
    private String absPathMusic = FileHelper.initStorge(mContext).getFile(Constant.PATH_MUSIC_APP).getPath();
    private String absPathMusicArt = absPathMusic + File.separator + Constant.PATH_MUSIC_ART;

    public MyDownloader(final Context context) {
        this.mContext = context;
    }

    public void download(List<SongItem> album) {
        for (final SongItem songItem : album) {
            if (!FileHelper.isFileExistOnAppPath(mContext, songItem.getKeyMp3() + Constant.MUSIC_EXT)) {
                Request requestMusic = new Request(songItem.getMp3Url(), absPathMusic, songItem.getKeyMp3() + Constant.MUSIC_EXT);
                Request requestArt = new Request(songItem.getAvatar(), absPathMusicArt, songItem.getKeyMp3() + Constant.IMG_EXT);
                List<Request> requests = new ArrayList<>();
                requests.add(requestMusic);
                requests.add(requestArt);
                Fetch fetch = Fetch.getInstance(mContext);
                final List<Long> queueIds = fetch.enqueue(requests);
                fetch.addFetchListener(new FetchListener() {
                    @Override
                    public void onUpdate(long id, int status, int progress, long downloadedBytes, long fileSize, int error) {
                        switch (error) {
                            case Fetch.ERROR_NO_STORAGE_SPACE:
                                Toast.makeText(mContext, "Thiết bị không còn dung lượng trống!", Toast.LENGTH_LONG).show();
                                break;
                            default:
                                break;
                        }
                        switch (status) {
                            case Fetch.STATUS_DONE:
                                break;
                            case Fetch.STATUS_ERROR:
                                break;
                            default:
                                break;
                        }
                    }
                });
                fetch.release();
                insertDownloaded(songItem);
            } else {
                Toast.makeText(mContext, "Bạn đã tải bài " + songItem.getSongName(), Toast.LENGTH_LONG).show();
            }
        }
    }

    private void insertDownloaded(SongItem songItem) {
        String songName = songItem.getSongName();
        String singer = songItem.getSinger();
        String singerUrl = songItem.getSingerUrl();
        String bgImage = songItem.getBgimage();
        String fileName = songItem.getKeyMp3() + Constant.MUSIC_EXT;
        String songUrl = songItem.getSongUrl();
        String keyMp3 = songItem.getKeyMp3();
        int isUserLocal = Constant.IS_LOCAL_APP;

        String mp3Url = Constant.URL_LOCAL_FILE + absPathMusic + File.separator + keyMp3 + Constant.MUSIC_EXT;
        String avatar = Constant.URL_LOCAL_FILE + absPathMusicArt + File.separator + keyMp3 + Constant.IMG_EXT;

        SongDAO songDAO = new SongDAO(mContext, true);
        songDAO.addNewSong(songName, singer, singerUrl, bgImage, avatar, keyMp3, mp3Url, songUrl, fileName, isUserLocal);
        Toast.makeText(mContext, "Tải thành công bài hát " + songName, Toast.LENGTH_SHORT).show();
    }

}
