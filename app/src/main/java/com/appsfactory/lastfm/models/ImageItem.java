package com.appsfactory.lastfm.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Mhanna, Eyad on 16/02/2019.
 */

public class ImageItem implements Serializable {

    @SerializedName("#text")
    private String url;
    @SerializedName("size")
    private String size;

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

}
