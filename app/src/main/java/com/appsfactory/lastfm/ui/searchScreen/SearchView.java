package com.appsfactory.lastfm.ui.searchScreen;

import com.appsfactory.lastfm.models.Artist;
import com.appsfactory.lastfm.ui.general.BaseView;

import java.util.List;

/**
 * Created by Mhanna, Eyad on 17/02/2019.
 */

public interface SearchView extends BaseView {

    void showSearchResult(List<Artist> items);

}
