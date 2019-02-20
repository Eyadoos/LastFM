package com.appsfactory.lastfm.ui.mainScreen;

import com.appsfactory.lastfm.models.AlbumInfo;

import java.util.List;

/**
 * Created by Mhanna, Eyad on 16/02/2019.
 */

class MainPresenter {

    private MainView mainView;
    private MainInteractor mainInteractor;

    MainPresenter(MainView mainView, MainInteractor mainInteractor) {
        this.mainView = mainView;
        this.mainInteractor = mainInteractor;
    }

    void onResume() {

        if (mainView != null) mainView.showProgress();
        mainInteractor.getStoredAlbums(this::onFinishedLoadingStoredAlbums);

    }

    private void onFinishedLoadingStoredAlbums(List<AlbumInfo> storedAlbums) {
        if (mainView != null) {
            if (storedAlbums != null) {
                mainView.hideProgress();
                if (storedAlbums.size() == 0) {
                    mainView.showMessage("Your stored albums will appear here");
                } else {
                    mainView.showStoredAlbums(storedAlbums);
                }
            }
        }
    }

    void onDestroy() {
        mainView = null;
    }

}
