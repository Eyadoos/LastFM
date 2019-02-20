package com.appsfactory.lastfm.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mhanna, Eyad on 16/02/2019.
 */

public class AlbumInfoResponse {

    @SerializedName("album")
    AlbumInfo albumInfo;

    public AlbumInfo getAlbumInfo() {
        return albumInfo;
    }

    public void setAlbumInfo(AlbumInfo albumInfo) {
        this.albumInfo = albumInfo;
    }

}
