package com.appsfactory.lastfm.ui.topAlbumsScreen;

import com.appsfactory.lastfm.models.Album;
import com.appsfactory.lastfm.models.TopAlbumsResponse;
import com.appsfactory.lastfm.network.NetworkService;
import com.appsfactory.lastfm.utils.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mhanna, Eyad on 19/02/2019.
 */

public class TopAlbumsInteractor {

    interface OnFinishedListener {
        void onFinishedLoadingTopAlbums(List<Album> items);
    }

    void getTopAlbumsForArtist(OnFinishedListener listener, String artistName) {

        NetworkService.getInstance().getClient().topAlbumsForArtist(Constants.API_KEY, artistName).enqueue(new Callback<TopAlbumsResponse>() {
            @Override
            public void onResponse(Call<TopAlbumsResponse> call, Response<TopAlbumsResponse> response) {
                listener.onFinishedLoadingTopAlbums(response.body().getTopAlbums().getAlbums());
            }

            @Override
            public void onFailure(Call<TopAlbumsResponse> call, Throwable t) {
                listener.onFinishedLoadingTopAlbums(null);
            }
        });

    }

}
