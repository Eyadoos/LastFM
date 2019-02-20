package com.appsfactory.lastfm.ui.topAlbumsScreen;

import com.appsfactory.lastfm.models.Album;

import java.util.List;

/**
 * Created by Mhanna, Eyad on 19/02/2019.
 */

public class TopAlbumsPresenter {

    private TopAlbumsInteractor topAlbumsInteractor;
    private TopAlbumsView topAlbumsView;

    public TopAlbumsPresenter(TopAlbumsInteractor topAlbumsInteractor, TopAlbumsView topAlbumsView) {
        this.topAlbumsInteractor = topAlbumsInteractor;
        this.topAlbumsView = topAlbumsView;
    }

    void getTopAlbumsForArtist(String artistName) {

        if (topAlbumsView != null) topAlbumsView.showProgress();
        topAlbumsInteractor.getTopAlbumsForArtist(this::onFinishedLoadingTopAlbums, artistName);

    }

    private void onFinishedLoadingTopAlbums(List<Album> items) {

        topAlbumsView.hideProgress();

        if (items == null) {
            topAlbumsView.showMessage("Connection Error");
            return;
        }

        if (items.size() <= 0) topAlbumsView.showMessage("We couldn't find any albums for that artist");
        else topAlbumsView.showTopAlbums(items);

    }

    void onDestroy() {
        topAlbumsView = null;
    }

}
