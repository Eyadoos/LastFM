package com.appsfactory.lastfm.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mhanna, Eyad on 17/02/2019.
 */

public class SearchForArtistResponse {

    @SerializedName("results")
    private SearchForArtistResult result;

    public SearchForArtistResult getResult() {
        return result;
    }

    public void setResult(SearchForArtistResult result) {
        this.result = result;
    }
}
