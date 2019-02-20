package com.appsfactory.lastfm.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mhanna, Eyad on 17/02/2019.
 */

public class Tracks implements Serializable {

    @SerializedName("track")
    private List<Track> tracksList;

    public List<Track> getTracksList() {
        return tracksList;
    }

    public void setTracksList(List<Track> tracksList) {
        this.tracksList = tracksList;
    }

}
