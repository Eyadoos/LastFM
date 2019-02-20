package com.appsfactory.lastfm.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mhanna, Eyad on 16/02/2019.
 */

public class TopAlbumsResponse {

    @SerializedName("topalbums")
    private TopAlbums topAlbums;

    public TopAlbums getTopAlbums() {
        return topAlbums;
    }

    public void setTopAlbums(TopAlbums topAlbums) {
        this.topAlbums = topAlbums;
    }

}
