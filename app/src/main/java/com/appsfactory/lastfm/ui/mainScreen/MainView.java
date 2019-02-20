package com.appsfactory.lastfm.ui.mainScreen;

import com.appsfactory.lastfm.models.AlbumInfo;
import com.appsfactory.lastfm.ui.general.BaseView;

import java.util.List;

/**
 * Created by Mhanna, Eyad on 16/02/2019.
 */

public interface MainView extends BaseView {

    void showStoredAlbums(List<AlbumInfo> items);

}
