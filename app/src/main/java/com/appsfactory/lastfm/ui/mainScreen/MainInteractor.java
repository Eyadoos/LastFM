package com.appsfactory.lastfm.ui.mainScreen;

import android.content.Context;
import android.os.AsyncTask;

import com.appsfactory.lastfm.db.AlbumDatabase;
import com.appsfactory.lastfm.models.AlbumInfo;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by Mhanna, Eyad on 16/02/2019.
 */

class MainInteractor {

    private Context context;

    MainInteractor(Context context) {
        this.context = context;
    }

    interface OnFinishedListener {
        void onFinishedLoadingStoredAlbums(List<AlbumInfo> items);
    }

    void getStoredAlbums(OnFinishedListener listener) {
        AsyncTask.execute(() -> {
            List<AlbumInfo> data = AlbumDatabase.getAlbumDatabase(context).albumDao().getAll();
            ((AppCompatActivity) context).runOnUiThread(() -> listener.onFinishedLoadingStoredAlbums(data));
        });
    }

}
