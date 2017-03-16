package dev.ongteu.bkmusic.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mohammad.songig.common.PlayerException;
import com.github.mohammad.songig.common.SongigPlayer;
import com.github.mohammad.songig.listener.OnBeforePrepareListener;
import com.github.mohammad.songig.model.Song;

import java.util.List;

import dev.ongteu.bkmusic.R;
import dev.ongteu.bkmusic.data.model.PlaylistItem;
import dev.ongteu.bkmusic.data.model.SongItem;
import dev.ongteu.bkmusic.data.table.GetSongs;
import dev.ongteu.bkmusic.adapter.NowListAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NowListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NowListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NowListFragment extends Fragment {
    private static final String ARG_PLAY_TYPE = "playType";
    private static final String ARG_SONG_URL = "songUrl";

    private int mPlayType;
    private String mSongUrl;
    private List<Song> mPlaylist;

    private OnFragmentInteractionListener mListener;

    public NowListFragment() {
    }

    /**
     * * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param playType
     * @param url
     * @return A new instance of fragment MainActivityFragment.
     */
    public static NowListFragment newInstance(int playType, String url) {
        NowListFragment fragment = new NowListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PLAY_TYPE, playType);
        args.putString(ARG_SONG_URL, url);
        fragment.setArguments(args);
        return fragment;
    }

    public static NowListFragment newInstance() {
        NowListFragment fragment = new NowListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPlayType = getArguments().getInt(ARG_PLAY_TYPE);
            mSongUrl = getArguments().getString(ARG_SONG_URL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hotmusic_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            NowListAdapter nowListAdapter = new NowListAdapter(GetSongs.ITEMS, mListener, context);
            mPlaylist = GetSongs.SONGIG_ITEMS;
            new GetSongs(context, mSongUrl, nowListAdapter);
            recyclerView.setAdapter(nowListAdapter);
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        SongigPlayer.getInstance(view.getContext()).getPlayList().addAll(mPlaylist);
//        try {
//            SongigPlayer.getInstance(view.getContext()).play(0);
//        } catch (PlayerException e) {
//            e.printStackTrace();
//        }
    }

    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

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
        void onFragmentInteraction(SongItem songItem);
    }
}
