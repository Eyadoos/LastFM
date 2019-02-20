package com.appsfactory.lastfm.ui.topAlbumsScreen;

import com.appsfactory.lastfm.models.Album;
import com.appsfactory.lastfm.ui.general.BaseView;

import java.util.List;

/**
 * Created by Mhanna, Eyad on 19/02/2019.
 */

public interface TopAlbumsView extends BaseView {

    void showTopAlbums(List<Album> items);

}
