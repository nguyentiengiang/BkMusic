package dev.ongteu.bkmusic.data.parser;

/**
 * Created by TienGiang on 25/9/2016.
 */

public interface IResponse {
    /**
     * Called upon successful completion of a HTTP request with the JSON that the server responded with.
     *
     * @param json
     */
    void success(String json);

    /**
     * Called if the HTTP request fails for any reason.
     *
     * @param ex
     */
    void fail(Exception ex);
}
