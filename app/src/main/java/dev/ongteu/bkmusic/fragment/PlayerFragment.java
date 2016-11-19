package dev.ongteu.bkmusic.fragment;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.mohammad.songig.common.PlayerException;

import co.mobiwise.library.InteractivePlayerView;
import co.mobiwise.library.OnActionClickedListener;
import dev.ongteu.bkmusic.R;
import dev.ongteu.bkmusic.utils.MySongigPlayer;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PlayerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PlayerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlayerFragment extends Fragment implements OnActionClickedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SONG_URL = "songUrl";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mSongUrl;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PlayerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param songUrl Parameter For String URL Playlist or Song.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PlayerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PlayerFragment newInstance(String songUrl, String param2) {
        PlayerFragment fragment = new PlayerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SONG_URL, songUrl);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mSongUrl = getArguments().getString(ARG_SONG_URL);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewRoot = inflater.inflate(R.layout.fragment_player, container, false);

//        PlayerAdapter adapter = new PlayerAdapter(viewRoot.getContext(), PlaylistTable.ITEMS);
//        String urlSong = "http://www.nhaccuatui.com/bai-hat/chung-ta-khong-thuoc-ve-nhau-son-tung-m-tp.Qtd3XdEr5XtP.html";
//        PlaylistTable playlistSong = new PlaylistTable(viewRoot.getContext(), adapter, urlSong);

//        PlayerAdapter adapter = new PlayerAdapter(viewRoot.getContext(), GetSongs.ITEMS.get(0));
//        GetSongs songs = new GetSongs(viewRoot.getContext(), mSongUrl);
//        TextView txt = (TextView) viewRoot.findViewById(R.id.musicTitle);


        try {
//            MySongigPlayer.getPlayer().getPlayList().addAll(songs.SONGIG_ITEMS);
            MySongigPlayer.getPlayer().play(0);
            boolean val = MySongigPlayer.getPlayer().isOneTrackPlaying();
            Log.e("DEBUG >>>>>>>>>>>>>>>1", "RUN PASS");
            Log.e("DEBUG >>>>>>>>>>>>>>>2", MySongigPlayer.getPlayer().getPlayList().get(0).getName());
        } catch (PlayerException e) {
            e.printStackTrace();
        }

        final InteractivePlayerView mInteractivePlayerView = (InteractivePlayerView) viewRoot.findViewById(R.id.interactivePlayerView);
        mInteractivePlayerView.setMax(114);
        mInteractivePlayerView.setProgress(50);
        mInteractivePlayerView.setOnActionClickedListener(this);

        final ImageView imageViewControl = (ImageView) viewRoot.findViewById(R.id.control);
        imageViewControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mInteractivePlayerView.isPlaying()) {
                    mInteractivePlayerView.start();
                    imageViewControl.setBackgroundResource(R.drawable.ic_action_pause);
                } else {
                    mInteractivePlayerView.stop();
                    imageViewControl.setBackgroundResource(R.drawable.ic_action_play);
                }
            }
        });

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
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onActionClicked(int id) {
        switch (id) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                break;
        }
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
