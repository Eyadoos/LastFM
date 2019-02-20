package com.appsfactory.lastfm.ui.albumDetailsScreen;

import com.appsfactory.lastfm.models.AlbumInfo;
import com.appsfactory.lastfm.ui.general.BaseView;
import com.cunoraz.tagview.Tag;

import java.util.List;

/**
 * Created by Mhanna, Eyad on 19/02/2019.
 */

public interface AlbumInfoView extends BaseView {

    void setupAlbumHeader(String albumTitle, String imageURL);
    void showAlbumInfo(AlbumInfo albumInfo);
    void setTags(List<Tag> albumTags);
    void showAlbumStatus(boolean isStoredInDB);

}
