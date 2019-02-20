package com.appsfactory.lastfm.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mhanna, Eyad on 17/02/2019.
 */

public class Tags implements Serializable {

    @SerializedName("tag")
    private List<Tag> tagsList;

    public List<Tag> getTagsList() {
        return tagsList;
    }

    public void setTagsList(List<Tag> tagsList) {
        this.tagsList = tagsList;
    }

}
