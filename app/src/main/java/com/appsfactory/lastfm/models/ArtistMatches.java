package com.appsfactory.lastfm.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Mhanna, Eyad on 17/02/2019.
 */

public class ArtistMatches {

    @SerializedName("artist")
    private List<Artist> artists;

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

}
