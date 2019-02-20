package com.appsfactory.lastfm.ui.albumDetailsScreen;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.appsfactory.lastfm.R;
import com.appsfactory.lastfm.models.Album;
import com.appsfactory.lastfm.models.AlbumInfo;
import com.bumptech.glide.Glide;
import com.cunoraz.tagview.Tag;
import com.cunoraz.tagview.TagView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AlbumInfoActivity extends AppCompatActivity implements AlbumInfoView {

    @BindView(R.id.album_image_view)
    ImageView albumImageView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.store_album_button)
    FloatingActionButton storeAlbumButton;
    @BindView(R.id.published_date)
    TextView publishedDate;
    @BindView(R.id.tag_group)
    TagView tagGroup;
    @BindView(R.id.album_content_text_view)
    ExpandableTextView albumContentTextView;
    @BindView(R.id.listeners_count_text_view)
    TextView listenersCountTextView;
    @BindView(R.id.play_count_text_view)
    TextView playCountTextView;
    @BindView(R.id.album_tracks_recycler_view)
    RecyclerView albumTracksRecyclerView;
    @BindView(R.id.info_message)
    TextView infoMessage;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    private AlbumInfo albumInfo;
    private AlbumInfoPresenter albumInfoPresenter;
    private boolean isStoredInDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_info);
        ButterKnife.bind(this);

        albumInfoPresenter = new AlbumInfoPresenter(new AlbumInfoInteractor(AlbumInfoActivity.this), this);
        albumInfoPresenter.getAlbumInfo(getIntent());

    }

    @OnClick(R.id.store_album_button)
    public void onViewClicked() {
        if (isStoredInDB()) {
            albumInfoPresenter.deleteAlbum(getAlbumInfo());
            Snackbar.make(storeAlbumButton, "Album Deleted", Snackbar.LENGTH_LONG).show();
        } else {
            albumInfoPresenter.insertAlbum(getAlbumInfo());
            Snackbar.make(storeAlbumButton, "Album Stored", Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void setupAlbumHeader(String albumTitle, String imageURL) {

        Glide.with(this).load(imageURL).into(albumImageView);
        toolbar.setTitle(albumTitle);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    @Override
    public void showAlbumInfo(AlbumInfo albumInfo) {

        // Set the data of the Album we received from the API to the correct views
        setAlbumInfo(albumInfo);
        if (albumInfo.getWiki() != null) {
            publishedDate.setText(albumInfo.getWiki().getTimePublished());
            albumContentTextView.setText(Html.fromHtml(albumInfo.getWiki().getContent()));
        } else {
            publishedDate.setVisibility(View.GONE);
            albumContentTextView.setVisibility(View.GONE);
        }

        listenersCountTextView.setText(albumInfo.getFormattedListenersCount());
        playCountTextView.setText(albumInfo.getFormattedPlayCount());
        albumTracksRecyclerView.setAdapter(new AlbumTracksAdapter(albumInfo.getTracks().getTracksList(), albumInfo.getImageUrl()));

    }

    @Override
    public void setTags(List<Tag> albumTags) {
        tagGroup.addTags(albumTags);
    }

    @Override
    public void showAlbumStatus(boolean isStoredInDB) {
        setStoredInDB(isStoredInDB);
        if (isStoredInDB()) {
            storeAlbumButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_filled));
            storeAlbumButton.setColorFilter(Color.parseColor("#FFD700"));
        } else {
            storeAlbumButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_border));
            storeAlbumButton.setColorFilter(Color.parseColor("#FFFFFF"));
        }
    }

    @Override
    public void showProgress() {
        // Show a progress bar in the middle of the screen to indicate that we are fetching the data
        infoMessage.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        // Hide the progress bar to indicate that the fetching process has finished
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showMessage(String message) {
        // If the fetching process failed or there is no data then hide all other views and show a message to the user
        infoMessage.setText(message);
        infoMessage.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onDestroy() {
        albumInfoPresenter.onDestroy();
        super.onDestroy();
    }

    public boolean isStoredInDB() {
        return isStoredInDB;
    }

    public void setStoredInDB(boolean storedInDB) {
        isStoredInDB = storedInDB;
    }

    public AlbumInfo getAlbumInfo() {
        return albumInfo;
    }

    public void setAlbumInfo(AlbumInfo albumInfo) {
        this.albumInfo = albumInfo;
    }
}
