package com.appsfactory.lastfm.ui.searchScreen;

import com.appsfactory.lastfm.models.Artist;
import com.appsfactory.lastfm.models.SearchForArtistResponse;
import com.appsfactory.lastfm.network.NetworkService;
import com.appsfactory.lastfm.utils.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mhanna, Eyad on 17/02/2019.
 */

class SearchInteractor {

    interface OnFinishedListener {
        void onFinishedLoadingSearchResult(List<Artist> items);
    }

    void searchForArtist(OnFinishedListener listener, String searchKey) {

        NetworkService.getInstance().getClient().searchForArtist(Constants.API_KEY, searchKey).enqueue(new Callback<SearchForArtistResponse>() {
            @Override
            public void onResponse(Call<SearchForArtistResponse> call, Response<SearchForArtistResponse> response) {
                listener.onFinishedLoadingSearchResult(response.body().getResult().getArtistMatches().getArtists());
            }

            @Override
            public void onFailure(Call<SearchForArtistResponse> call, Throwable t) {
                listener.onFinishedLoadingSearchResult(null);
            }
        });

    }

}
