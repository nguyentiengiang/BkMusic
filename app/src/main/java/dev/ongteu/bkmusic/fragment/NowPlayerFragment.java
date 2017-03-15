package dev.ongteu.bkmusic.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import dev.ongteu.bkmusic.R;
import dev.ongteu.bkmusic.data.model.SongItem;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NowPlayerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NowPlayerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NowPlayerFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SONG_NAME = "songName";
    private static final String ARG_SONG_ARTIST = "songArtist";
    private static final String ARG_SONG_COVER = "songCover";
    private static final String ARG_SONG_DURATION = "songDuration";

    // TODO: Rename and change types of parameters
    private String mSongName;
    private String mSongArtist;
    private String mSongCover;
    private String mSongDuration;

    private OnFragmentInteractionListener mListener;

    public NowPlayerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param songName
     * @param songArtist
     * @param songCover
     * @param songDuration
     * @return
     */
    // TODO: Rename and change types and number of parameters
    public static NowPlayerFragment newInstance(String songName, String songArtist, String songCover, String songDuration) {
        NowPlayerFragment fragment = new NowPlayerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SONG_NAME, songName);
        args.putString(ARG_SONG_ARTIST, songArtist);
        args.putString(ARG_SONG_COVER, songCover);
        args.putString(ARG_SONG_DURATION, songDuration);
        fragment.setArguments(args);
        return fragment;
    }

    public static NowPlayerFragment newInstance() {
        NowPlayerFragment fragment = new NowPlayerFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mSongName = getArguments().getString(ARG_SONG_NAME);
            mSongArtist = getArguments().getString(ARG_SONG_ARTIST);
            mSongCover = getArguments().getString(ARG_SONG_COVER);
            mSongDuration = getArguments().getString(ARG_SONG_DURATION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player, container, false);
        return view;
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
}
