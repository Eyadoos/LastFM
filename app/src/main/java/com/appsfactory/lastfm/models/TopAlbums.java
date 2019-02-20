package com.appsfactory.lastfm.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Mhanna, Eyad on 16/02/2019.
 */

public class TopAlbums {

    @SerializedName("album")
    private List<Album> albums;

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

}
