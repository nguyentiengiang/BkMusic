package dev.ongteu.bkmusic.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.List;

import dev.ongteu.bkmusic.R;
import dev.ongteu.bkmusic.adapter.PlaylistAdapter;
import dev.ongteu.bkmusic.data.dao.PlaylistDAO;
import dev.ongteu.bkmusic.data.dao.SongDAO;
import dev.ongteu.bkmusic.data.entity.Playlist;
import dev.ongteu.bkmusic.data.entity.PlaylistSong;
import dev.ongteu.bkmusic.data.entity.Songs;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PlaylistFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PlaylistFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlaylistFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private ListView mListView;

    public PlaylistFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainActivityFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PlaylistFragment newInstance(String param1, String param2) {
        PlaylistFragment fragment = new PlaylistFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static PlaylistFragment newInstance() {
        PlaylistFragment fragment = new PlaylistFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View viewRoot = inflater.inflate(R.layout.fragment_playlist, container, false);
        final Context context = viewRoot.getContext();
        if (viewRoot instanceof CoordinatorLayout) {
            final ListView listViewPlaylist = (ListView) viewRoot.findViewById(R.id.listViewPlaylist);

            refreshAdapter(context, listViewPlaylist);

            FloatingActionButton fabCreatePlaylist = (FloatingActionButton) viewRoot.findViewById(R.id.fabAddPlaylist);
            fabCreatePlaylist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showCreatePlaylist(context, listViewPlaylist, "", "");
                }
            });
        }
        return viewRoot;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public static void refreshAdapter(final Context context, ListView listView) {
        final PlaylistDAO playlistDAO = new PlaylistDAO(context);
        final List<Playlist> playLists = playlistDAO.getAll();
        final PlaylistAdapter playlistAdapter = new PlaylistAdapter(playLists, context, listView);
        listView.setAdapter(playlistAdapter);
        playlistAdapter.notifyDataSetChanged();
    }

    private void showCreatePlaylist(final Context context, final ListView listView, String contentString, CharSequence suggestString) {
        MaterialDialog materialDialog = new MaterialDialog.Builder(context)
                .title("Tạo playlist")
                .content(contentString)
                .positiveText("Tạo mới")
                .negativeText("Hủy bỏ")
                .inputType(InputType.TYPE_CLASS_TEXT)
                .input(R.string.create_playlist_hint, R.string.create_playlist_suggest, new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(MaterialDialog dialog, CharSequence input) {
                    }
                })
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        final PlaylistDAO playlistDAO = new PlaylistDAO(context);
                        String inputPlaylistName = dialog.getInputEditText().getText().toString();
                        if (playlistDAO.isDuplicateName(String.valueOf(inputPlaylistName))) {
                            showCreatePlaylist(context, listView, "Tên playlist đã có", inputPlaylistName);
                        } else {
                            long playlistIdInserted = playlistDAO.createNewPlaylist(inputPlaylistName);
                            refreshAdapter(context, listView);
                            showEditSong(context, listView, playlistIdInserted);
                        }
                    }
                }).show();
        if (!suggestString.equals("")) {
            materialDialog.getInputEditText().setText(suggestString);
        }
    }

    public static void showEditSong(final Context context, final ListView listView, final long playlistId) {
        final List<Songs> allSong = new SongDAO(context, true).getAll();
        final PlaylistDAO playlistDAO = new PlaylistDAO(context);
        List<PlaylistSong> songOnList = playlistDAO.getSongEnityByPlaylist(playlistId);
        Integer[] idsPreSelected = getIndexPlaylistSelected(allSong, songOnList);
        new MaterialDialog.Builder(context)
                .title("Add song to playlist")
                .items(allSong)
                .itemsCallbackMultiChoice(idsPreSelected, new MaterialDialog.ListCallbackMultiChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, Integer[] which, CharSequence[] text) {
                        playlistDAO.removeAllSongOfPlaylist(playlistId);
                        for (Integer i : which) {
                            String key = getSongKeyByIndex(allSong, i);
                            playlistDAO.addSongItemToPlaylist(playlistId, key);
                        }
                        refreshAdapter(context, listView);
                        return true;
                    }
                })
                .positiveText("Chấp nhận")
                .negativeText("Bỏ qua")
                .show();
    }

    public static Integer[] getIndexPlaylistSelected(List<Songs> allSong, List<PlaylistSong> songOnList) {
        List<Integer> idsMap = new ArrayList<Integer>();
        for (int i = 0; i < allSong.size(); i++) {
            for (PlaylistSong s : songOnList) {
                if (s.getSongId().equals(allSong.get(i).getKeyMp3())) {
                    idsMap.add(i);
                    break;
                }
            }
        }
        Integer[] idsSelected = new Integer[idsMap.size()];
        idsSelected = idsMap.toArray(idsSelected);
        return idsSelected;
    }

    public static String getSongKeyByIndex(List<Songs> allSong, int idx) {
        String songKey = "";
        for (int i = 0; i < allSong.size(); i++) {
            if (i == idx) {
                songKey = allSong.get(i).getKeyMp3();
                break;
            }
        }
        return songKey;
    }

}
