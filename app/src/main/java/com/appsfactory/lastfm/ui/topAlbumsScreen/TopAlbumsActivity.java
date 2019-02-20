package com.appsfactory.lastfm.ui.topAlbumsScreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.appsfactory.lastfm.R;
import com.appsfactory.lastfm.models.Album;
import com.appsfactory.lastfm.models.Artist;
import com.appsfactory.lastfm.ui.albumDetailsScreen.AlbumInfoActivity;
import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TopAlbumsActivity extends AppCompatActivity implements TopAlbumsView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.artist_image_view)
    ImageView artistImageView;
    @BindView(R.id.top_albums_recycler_view)
    RecyclerView topAlbumsRecyclerView;
    @BindView(R.id.info_message)
    TextView infoMessage;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    private Artist artist;
    private TopAlbumsPresenter topAlbumsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_albums);
        ButterKnife.bind(this);

        if (getIntent().hasExtra("artist")) {
            setArtist((Artist) getIntent().getSerializableExtra("artist"));
        }

        Glide.with(this).load(getArtist().getImageUrl()).into(artistImageView);
        toolbar.setTitle(getArtist().getName());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        topAlbumsPresenter = new TopAlbumsPresenter(new TopAlbumsInteractor(), this);
        topAlbumsPresenter.getTopAlbumsForArtist(getArtist().getName());

    }

    @Override
    protected void onDestroy() {
        topAlbumsPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void showTopAlbums(List<Album> items) {
        // If the fetching process was successful then hide all other views and show the data
        infoMessage.setVisibility(View.GONE);

        topAlbumsRecyclerView.setAdapter(new TopAlbumsAdapter(items, this::onAlbumClicked));
        topAlbumsRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgress() {
        // Show a progress bar in the middle of the screen to indicate that we are fetching the data
        topAlbumsRecyclerView.setVisibility(View.GONE);
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
        topAlbumsRecyclerView.setVisibility(View.GONE);

        infoMessage.setText(message);
        infoMessage.setVisibility(View.VISIBLE);
    }

    public void onAlbumClicked(Object album) {
        // Open the list of all top albums when clicked on an artist
        Intent intent = new Intent(TopAlbumsActivity.this, AlbumInfoActivity.class);
        intent.putExtra("album", ((Album) album));
        startActivity(intent);
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }


}
