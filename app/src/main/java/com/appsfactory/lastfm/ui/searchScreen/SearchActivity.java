package com.appsfactory.lastfm.ui.searchScreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.appsfactory.lastfm.R;
import com.appsfactory.lastfm.models.Artist;
import com.appsfactory.lastfm.ui.topAlbumsScreen.TopAlbumsActivity;
import com.appsfactory.lastfm.utils.AppUtils;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity implements SearchView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.artist_name_edit_text)
    EditText artistNameEditText;
    @BindView(R.id.artist_name_text_input_layout)
    TextInputLayout artistNameTextInputLayout;
    @BindView(R.id.search_recycler_view)
    RecyclerView searchRecyclerView;
    @BindView(R.id.info_message)
    TextView infoMessage;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.search_button)
    ImageButton searchButton;

    private SearchPresenter searchPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        toolbar.setTitle(getResources().getString(R.string.app_name));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        searchPresenter = new SearchPresenter(new SearchInteractor(), this);
        artistNameEditText.setOnEditorActionListener((v, actionId, event) -> {

            if ((actionId == EditorInfo.IME_ACTION_SEARCH)) {
                if (artistNameEditText.getText().length() > 0) {
                    searchPresenter.searchForArtist(artistNameEditText.getText().toString());
                    AppUtils.hideKeyboard(SearchActivity.this);
                }
                return true;
            }
            return false;

        });

    }

    @Override
    protected void onDestroy() {
        searchPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void showSearchResult(List<Artist> items) {
        // If the fetching process was successful then hide all other views and show the data
        infoMessage.setVisibility(View.GONE);

        searchRecyclerView.setAdapter(new SearchAdapter(items, this::onArtistClicked));
        searchRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgress() {
        // Show a progress bar in the middle of the screen to indicate that we are fetching the data
        searchRecyclerView.setVisibility(View.GONE);
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
        searchRecyclerView.setVisibility(View.GONE);

        infoMessage.setText(message);
        infoMessage.setVisibility(View.VISIBLE);
    }

    public void onArtistClicked(Object artist) {
        // Open the list of all top albums when clicked on an artist
        Intent intent = new Intent(SearchActivity.this, TopAlbumsActivity.class);
        intent.putExtra("artist", ((Artist) artist));
        startActivity(intent);
    }

    @OnClick(R.id.search_button)
    public void onViewClicked() {
        if (artistNameEditText.getText().length() > 0) {
            searchPresenter.searchForArtist(artistNameEditText.getText().toString());
            AppUtils.hideKeyboard(SearchActivity.this);
        }
    }
}
