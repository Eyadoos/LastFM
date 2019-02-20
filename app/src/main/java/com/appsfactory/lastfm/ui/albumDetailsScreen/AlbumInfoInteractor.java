package com.appsfactory.lastfm.ui.albumDetailsScreen;

import android.content.Context;
import android.os.AsyncTask;

import com.appsfactory.lastfm.db.AlbumDatabase;
import com.appsfactory.lastfm.models.AlbumInfo;
import com.appsfactory.lastfm.models.AlbumInfoResponse;
import com.appsfactory.lastfm.network.NetworkService;
import com.appsfactory.lastfm.utils.Constants;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mhanna, Eyad on 19/02/2019.
 */

public class AlbumInfoInteractor {

    private Context context;

    AlbumInfoInteractor(Context context) {
        this.context = context;
    }

    interface OnFinishedListener {
        void onFinishedLoadingAlbumInfoFromAPI(AlbumInfo albumInfo);
        void onFinishedLoadingAlbumInfoFromDB(AlbumInfo albumInfo);
        void onFinishedInsertingAlbum();
        void onFinishedDeletingAlbum();
    }

    void getAlbumInfo(OnFinishedListener listener, String artistName, String albumName) {

        NetworkService.getInstance().getClient().getAlbumInfo(Constants.API_KEY, artistName, albumName).enqueue(new Callback<AlbumInfoResponse>() {
            @Override
            public void onResponse(Call<AlbumInfoResponse> call, Response<AlbumInfoResponse> response) {
                listener.onFinishedLoadingAlbumInfoFromAPI(response.body().getAlbumInfo());
            }

            @Override
            public void onFailure(Call<AlbumInfoResponse> call, Throwable t) {
                listener.onFinishedLoadingAlbumInfoFromAPI(null);
            }
        });

    }

    void getAlbumByName(OnFinishedListener listener, String albumName, String artistName) {
        AsyncTask.execute(() -> {
            AlbumInfo albumInfo = AlbumDatabase.getAlbumDatabase(context).albumDao().getAlbumByName(albumName, artistName);
            ((AppCompatActivity) context).runOnUiThread(() -> listener.onFinishedLoadingAlbumInfoFromDB(albumInfo));
        });
    }

    void insertAlbum(OnFinishedListener listener, AlbumInfo albumInfo) {
        AsyncTask.execute(() -> {
            AlbumDatabase.getAlbumDatabase(context).albumDao().insert(albumInfo);
            ((AppCompatActivity) context).runOnUiThread(listener::onFinishedInsertingAlbum);
        });
    }

    void deleteAlbum(OnFinishedListener listener, AlbumInfo albumInfo) {
        AsyncTask.execute(() -> {
            AlbumDatabase.getAlbumDatabase(context).albumDao().delete(albumInfo);
            ((AppCompatActivity) context).runOnUiThread(listener::onFinishedDeletingAlbum);
        });
    }


}
