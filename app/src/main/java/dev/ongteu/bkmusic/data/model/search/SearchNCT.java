
package dev.ongteu.bkmusic.data.model.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchNCT {

    @SerializedName("error_message")
    @Expose
    private String errorMessage;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("error_code")
    @Expose
    private long errorCode;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SearchNCT() {
    }

    /**
     * 
     * @param errorMessage
     * @param errorCode
     * @param data
     */
    public SearchNCT(String errorMessage, Data data, long errorCode) {
        super();
        this.errorMessage = errorMessage;
        this.data = data;
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public long getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(long errorCode) {
        this.errorCode = errorCode;
    }

}
