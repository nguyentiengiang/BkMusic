package dev.ongteu.bkmusic.data.parser;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.github.mohammad.songig.common.PlayMode;
import com.github.mohammad.songig.common.PlayerException;
import com.github.mohammad.songig.common.SongigPlayer;
import com.github.mohammad.songig.listener.OnPlayListener;
import com.github.mohammad.songig.model.Song;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dev.ongteu.bkmusic.R;
import dev.ongteu.bkmusic.data.model.SongItem;
//import dev.ongteu.bkmusic.data.table.GetSongs;
import dev.ongteu.bkmusic.utils.MySongigPlayer;

/**
 * Created by TienGiang on 25/9/2016.
 */

public class API {
    /*
	 * Bundles any additional parameters you want to pass to the server with the
	 * authentication parameters. Also adds the required RESTResponse callback
	 * class, and server URL to the dictionary.
	 */
    public static HashMap<String, Object> getPackedParameters(
            String serverURL, Map<String, Object> additionalParams,
            IResponse response, RecyclerView.Adapter adapter) {
        HashMap<String, Object> packedParams = new HashMap<String, Object>();

        packedParams.put(GetData.SERVER_URL, serverURL);

        if (additionalParams != null) {
            packedParams.putAll(additionalParams);
        }

        packedParams.put(GetData.CALLBACK_CLASS, response);
        packedParams.put(GetData.VIEW_ADAPTER, adapter);

        return packedParams;
    }

    public static class GetData extends
            AsyncTask<HashMap<String, Object>, Void, String> {

        public static final String TAG = "GETDATA";

        public static final String CALLBACK_CLASS = "CallbackClass";
        public static final String SERVER_URL = "ServerURL";
        public static final String VIEW_ADAPTER = "VIEW_ADAPTER";
        private RecyclerView.Adapter adapterView = null;

        ProgressDialog dialogue;

        private Context context;

        /**
         * Takes and InputStream and reads it's contents into a String.
         *
         * @param is
         * @return
         */
        private static String convertStreamToString(InputStream is) {
            java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
            return s.hasNext() ? s.next() : "";
        }

        public GetData(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            dialogue = new ProgressDialog(context);
            dialogue.setTitle(context.getString(R.string.waitting_text));
            dialogue.show();
            super.onPreExecute();

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            dialogue.dismiss();
            if (adapterView != null) {
                adapterView.notifyDataSetChanged();
            }
        }

        @Override
        protected String doInBackground(HashMap<String, Object>... params) {

            // Get callback class
            IResponse callback = null;
            if (params[0].containsKey(CALLBACK_CLASS)) {
                callback = (IResponse) params[0].get(CALLBACK_CLASS);
            } else {
                callback = new IResponse() {

                    @Override
                    public void success(String json) {
                        Log.i(TAG, "Callback not implemented!");
                    }

                    @Override
                    public void fail(Exception ex) {
                        Log.i(TAG, "Callback not implemented!");
                    }
                };
            }

            if(params[0].containsKey(VIEW_ADAPTER)){
                adapterView = (RecyclerView.Adapter) params[0].get(VIEW_ADAPTER);
            }

            // Get url data
            String url = null;
            if (params[0].containsKey(SERVER_URL)) {
                url = params[0].get(SERVER_URL).toString();
            } else {
                Log.e(TAG, "No server URL provided.");
                callback.fail(new Exception("No server URL provided"));
                return null;
            }

            try {
                HttpClient client = new DefaultHttpClient();
                HttpGet get = new HttpGet(url);

                HttpResponse response = client.execute(get);
                StatusLine statusLine = response.getStatusLine();

                if (statusLine.getStatusCode() == 200) {
                    HttpEntity entity = response.getEntity();
                    InputStream content = entity.getContent();

                    try {
                        callback.success(GetData.convertStreamToString(content));
                    } catch (Exception ex) {
                        Log.e(TAG, "Failed to parse JSON due to: " + ex);
                        callback.fail(ex);
                    }
                } else {
                    Log.e(TAG, "Server responded with status code: "
                            + statusLine.getStatusCode() + " IN " + url);
                    callback.fail(new Exception(
                            "Server responded with response code: "
                                    + statusLine.getStatusCode()));
                }

            } catch (Exception ex) {
                Log.e(TAG, "Failed to send HTTP POST request due to: " + ex);
                callback.fail(ex);
            }

            return null;
        }

    }

    public static HashMap<String, Object> getSongPackedParameters(String serverURL, Map<String, Object> additionalParams, IResponse response) {
//    public static HashMap<String, Object> getSongPackedParameters(String serverURL, Map<String, Object> additionalParams, IResponse response, RecyclerView.Adapter adapterView) {
//    public static HashMap<String, Object> getSongPackedParameters(String serverURL, Map<String, Object> additionalParams, IResponse response, PagerAdapter adapterView) {
        HashMap<String, Object> packedParams = new HashMap<String, Object>();

        packedParams.put(GetSongData.SERVER_URL, serverURL);

        if (additionalParams != null) {
            packedParams.putAll(additionalParams);
        }

        packedParams.put(GetSongData.CALLBACK_CLASS, response);
//        packedParams.put(GetSongData.VIEW_ADAPTER, adapterView);

        return packedParams;
    }
    public static class GetSongData extends
            AsyncTask<HashMap<String, Object>, Void, String> {

        public static final String TAG = "GETDATA";

        public static final String CALLBACK_CLASS = "CallbackClass";
        public static final String SERVER_URL = "ServerURL";
        public static final String VIEW_ADAPTER = "VIEW_ADAPTER";
//        private PagerAdapter adapterView = null;

        ProgressDialog dialogue;

        private Context context;

        /**
         * Takes and InputStream and reads it's contents into a String.
         *
         * @param is
         * @return
         */
        private static String convertStreamToString(InputStream is) {
            java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
            return s.hasNext() ? s.next() : "";
        }

        public GetSongData(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            dialogue = new ProgressDialog(context);
            dialogue.setTitle(context.getString(R.string.waitting_text));
            dialogue.show();
            super.onPreExecute();

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            dialogue.dismiss();
//            if (adapterView != null) {
//                adapterView.notifyDataSetChanged();
                try {
                    SongigPlayer.getInstance(context).play(0);
                } catch (PlayerException e) {
                    e.printStackTrace();
                }
//            }
        }

        @Override
        protected String doInBackground(HashMap<String, Object>... params) {

            // Get callback class
            IResponse callback = null;
            if (params[0].containsKey(CALLBACK_CLASS)) {
                callback = (IResponse) params[0].get(CALLBACK_CLASS);
            } else {
                callback = new IResponse() {

                    @Override
                    public void success(String json) {
                        Log.i(TAG, "Callback not implemented!");
                    }

                    @Override
                    public void fail(Exception ex) {
                        Log.i(TAG, "Callback not implemented!");
                    }
                };
            }

//            if(params[0].containsKey(VIEW_ADAPTER)){
//                adapterView = (PagerAdapter) params[0].get(VIEW_ADAPTER);
//            }

            // Get url data
            String url = null;
            if (params[0].containsKey(SERVER_URL)) {
                url = params[0].get(SERVER_URL).toString();
            } else {
                Log.e(TAG, "No server URL provided.");
                callback.fail(new Exception("No server URL provided"));
                return null;
            }

            try {
                HttpClient client = new DefaultHttpClient();
                HttpGet get = new HttpGet(url);

                HttpResponse response = client.execute(get);
                StatusLine statusLine = response.getStatusLine();

                if (statusLine.getStatusCode() == 200) {
                    HttpEntity entity = response.getEntity();
                    InputStream content = entity.getContent();

                    try {
                        callback.success(GetData.convertStreamToString(content));
                    } catch (Exception ex) {
                        Log.e(TAG, "Failed to parse JSON due to: " + ex);
                        callback.fail(ex);
                    }
                } else {
                    Log.e(TAG, "Server responded with status code: "
                            + statusLine.getStatusCode() + " IN " + url);
                    callback.fail(new Exception(
                            "Server responded with response code: "
                                    + statusLine.getStatusCode()));
                }

            } catch (Exception ex) {
                Log.e(TAG, "Failed to send HTTP POST request due to: " + ex);
                callback.fail(ex);
            }

            return null;
        }

    }
}
