package com.jpp.foods.services.responses;

import com.google.gson.annotations.SerializedName;

/**
 * Class that represents the code response from the server.
 * 
 * @author Juan P. Peretti (peretti.juan@gmail.com)
 * 
 */
public class ResponseCode {


    @SerializedName("code")
    private int mResponseCode;

    public int getResponseCode() {
        return mResponseCode;
    }

    public void setResponseCode(int mResponseCode) {
        this.mResponseCode = mResponseCode;
    }

}
