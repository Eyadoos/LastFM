package com.appsfactory.lastfm.ui.albumDetailsScreen;

import android.content.Intent;
import android.graphics.Color;

import com.appsfactory.lastfm.models.Album;
import com.appsfactory.lastfm.models.AlbumInfo;
import com.cunoraz.tagview.Tag;

import java.util.ArrayList;

/**
 * Created by Mhanna, Eyad on 19/02/2019.
 */

public class AlbumInfoPresenter implements AlbumInfoInteractor.OnFinishedListener {

    private AlbumInfoInteractor albumInfoInteractor;
    private AlbumInfoView albumInfoView;

    public AlbumInfoPresenter(AlbumInfoInteractor albumInfoInteractor, AlbumInfoView albumInfoView) {
        this.albumInfoInteractor = albumInfoInteractor;
        this.albumInfoView = albumInfoView;
    }

    void getAlbumInfo(Intent intent) {

        if (intent.hasExtra("album")) {
            Album album = (Album) intent.getSerializableExtra("album");
            getAlbumInfo(album.getArtist().getName(), album.getName());
            albumInfoView.setupAlbumHeader(getAlbumTitle(album), album.getImageUrl());
        } else if (intent.hasExtra("album_info")) {
            AlbumInfo albumInfo = (AlbumInfo) intent.getSerializableExtra("album_info");
            albumInfoView.hideProgress();
            albumInfoView.setupAlbumHeader(getAlbumTitle(albumInfo), albumInfo.getImageUrl());
            albumInfoView.showAlbumInfo(albumInfo);
            prepareAlbumTags(albumInfo);
            albumInfoView.showAlbumStatus(true);
        }

    }

    void getAlbumInfo(String artistName, String albumName) {

        if (albumInfoView != null) albumInfoView.showProgress();
        albumInfoInteractor.getAlbumInfo(this, artistName, albumName);

    }

    void insertAlbum(AlbumInfo albumInfo) {

        // Call the interactor to insert all the Album info into the LOCAL DB
        albumInfoInteractor.insertAlbum(this, albumInfo);

    }

    void deleteAlbum(AlbumInfo albumInfo) {

        // Call the interactor to insert all the Album info into the LOCAL DB
        albumInfoInteractor.deleteAlbum(this, albumInfo);

    }

    private void prepareAlbumTags(AlbumInfo albumInfo) {

        // Iterate over all tags of the album and create corresponding model with the correct colors
        ArrayList<Tag> albumTags = new ArrayList<>();
        Tag tag;

        for (int i = 0; i < albumInfo.getAlbumTags().size(); i++) {
            tag = new Tag(albumInfo.getAlbumTags().get(i));
            tag.radius = 10f;
            tag.layoutColor = Color.parseColor("#BA0000");
            albumTags.add(tag);
        }

        albumInfoView.setTags(albumTags);

    }

    @Override
    public void onFinishedLoadingAlbumInfoFromAPI(AlbumInfo albumInfo) {

        albumInfoView.hideProgress();

        if (albumInfo == null) {
            albumInfoView.showMessage("Connection Error");
        } else {
            albumInfoView.showAlbumInfo(albumInfo);
            prepareAlbumTags(albumInfo);
        }

        // Get album from DB
        albumInfoInteractor.getAlbumByName(this, albumInfo.getName(), albumInfo.getArtistName());

    }

    @Override
    public void onFinishedLoadingAlbumInfoFromDB(AlbumInfo albumInfo) {

        // Check if the Album exists in the database then update the view accordingly
        albumInfoView.showAlbumStatus(albumInfo != null);

    }

    @Override
    public void onFinishedInsertingAlbum() {

        // Insertion operation was successful, update view accordingly
        albumInfoView.showAlbumStatus(true);

    }

    @Override
    public void onFinishedDeletingAlbum() {

        // Deletion operation was successful, update view accordingly
        albumInfoView.showAlbumStatus(false);

    }

    private String getAlbumTitle(Album album) {
        return album.getArtist().getName() + " - " + album.getName();
    }

    private String getAlbumTitle(AlbumInfo albumInfo) {
        return albumInfo.getArtistName() + " - " + albumInfo.getName();
    }

    void onDestroy() {
        albumInfoView = null;
    }

}
