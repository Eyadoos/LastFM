package com.appsfactory.lastfm.ui.searchScreen;

import com.appsfactory.lastfm.models.Artist;

import java.util.List;

/**
 * Created by Mhanna, Eyad on 17/02/2019.
 */

public class SearchPresenter {

    private SearchInteractor searchInteractor;
    private SearchView searchView;

    public SearchPresenter(SearchInteractor searchInteractor, SearchView searchView) {
        this.searchInteractor = searchInteractor;
        this.searchView = searchView;
    }

    void searchForArtist(String searchKey) {

        if (searchView != null) searchView.showProgress();
        searchInteractor.searchForArtist(this::onFinishedLoadingSearchResult, searchKey);

    }

    private void onFinishedLoadingSearchResult(List<Artist> items) {

        searchView.hideProgress();

        if (items == null) {
            searchView.showMessage("Connection Error");
            return;
        }

        if (items.size() <= 0) searchView.showMessage("We couldn't find any artists that match your search key");
        else searchView.showSearchResult(items);

    }

    void onDestroy() {
        searchView = null;
    }

}
