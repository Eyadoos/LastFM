package com.appsfactory.lastfm.models;

import com.appsfactory.lastfm.utils.Constants;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

/**
 * Created by Mhanna, Eyad on 16/02/2019.
 */

public class Artist implements Serializable {

    @SerializedName("mbid")
    private String mbid;
    @SerializedName("name")
    private String name;
    @SerializedName("listeners")
    private String listenersCount;
    @SerializedName("image")
    private List<ImageItem> images;
    @SerializedName("streamable")
    private String streamable;
    @SerializedName("url")
    private String url;

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ImageItem> getImages() {
        return images;
    }

    public void setImage(List<ImageItem> images) {
        this.images = images;
    }

    public String getStreamable() {
        return streamable.equalsIgnoreCase("1") ? "Artist is streamable" : "Artist is not streamable";
    }

    public void setStreamable(String streamable) {
        this.streamable = streamable;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        if (getImages() != null && getImages().size() > 0) {
            for (ImageItem img :
                    getImages()) {
                if (img.getSize().equalsIgnoreCase(Constants.IMAGE_SIZE)) {
                    return img.getUrl();
                }
            }
        }
        return null;
    }

    public String getListenersCount() {
        return String.format(Locale.US, "%,d", Integer.parseInt(listenersCount)) + " Listeners";
    }

    public void setListenersCount(String listenersCount) {
        this.listenersCount = listenersCount;
    }
}
