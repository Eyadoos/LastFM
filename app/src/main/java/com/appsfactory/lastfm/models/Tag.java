package com.appsfactory.lastfm.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Mhanna, Eyad on 17/02/2019.
 */

public class Tag implements Serializable {

    @SerializedName("name")
    private String name;
    @SerializedName("url")
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
