package com.appsfactory.lastfm.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Locale;

/**
 * Created by Mhanna, Eyad on 17/02/2019.
 */

public class Track implements Serializable {

    @SerializedName("name")
    private String name;
    @SerializedName("duration")
    private String duration;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return String.format(Locale.US, "%02d:%02d", ((Integer.parseInt(duration) % 3600) / 60), (Integer.parseInt(duration) % 60));
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

}
