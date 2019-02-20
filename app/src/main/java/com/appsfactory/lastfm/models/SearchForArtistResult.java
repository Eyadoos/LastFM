package com.appsfactory.lastfm.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mhanna, Eyad on 17/02/2019.
 */

public class SearchForArtistResult {

    @SerializedName("artistmatches")
    private ArtistMatches artistMatches;

    public ArtistMatches getArtistMatches() {
        return artistMatches;
    }

    public void setArtistMatches(ArtistMatches artistMatches) {
        this.artistMatches = artistMatches;
    }
}
