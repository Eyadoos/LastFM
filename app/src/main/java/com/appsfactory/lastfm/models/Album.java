package com.appsfactory.lastfm.models;

import com.appsfactory.lastfm.utils.Constants;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

/**
 * Created by Mhanna, Eyad on 16/02/2019.
 */

public class Album implements Serializable {

    @SerializedName("name")
    private String name;
    @SerializedName("playcount")
    private String playCount;
    @SerializedName("mbid")
    private String mbid;
    @SerializedName("url")
    private String url;
    @SerializedName("artist")
    private Artist artist;
    @SerializedName("image")
    private List<ImageItem> images;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ImageItem> getImages() {
        return images;
    }

    public void setImages(List<ImageItem> images) {
        this.images = images;
    }

    public String getPlaycount() {
        return "Played " + String.format(Locale.US, "%,d", Integer.parseInt(playCount)) + " times";
    }

    public void setPlaycount(String playcount) {
        this.playCount = playcount;
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        if (getImages() != null) {
            if (getImages().size() > 0) {
                for (ImageItem img : getImages()) {
                    if (img.getSize().equalsIgnoreCase(Constants.IMAGE_SIZE)) {
                        return img.getUrl();
                    }
                }
            }
        }
        return null;
    }

}
