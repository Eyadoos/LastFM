package com.appsfactory.lastfm.ui.mainScreen;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.appsfactory.lastfm.R;
import com.appsfactory.lastfm.db.AlbumDatabase;
import com.appsfactory.lastfm.models.AlbumInfo;
import com.appsfactory.lastfm.models.AlbumInfoResponse;
import com.appsfactory.lastfm.network.LastFMClient;
import com.appsfactory.lastfm.network.NetworkService;
import com.appsfactory.lastfm.ui.albumDetailsScreen.AlbumInfoActivity;
import com.appsfactory.lastfm.ui.searchScreen.SearchActivity;
import com.appsfactory.lastfm.utils.Constants;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MainView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.info_message)
    TextView infoMessage;
    @BindView(R.id.stored_albums_recycler_view)
    RecyclerView storedAlbumsRecyclerView;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        mainPresenter = new MainPresenter(this, new MainInteractor(this));

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        LastFMClient client = NetworkService.getInstance().getClient();
        Call<AlbumInfoResponse> albumInfoCall = client.getAlbumInfo(Constants.API_KEY, "Cher", "Believe");
        albumInfoCall.enqueue(new Callback<AlbumInfoResponse>() {
            @Override
            public void onResponse(Call<AlbumInfoResponse> call, final Response<AlbumInfoResponse> response) {

                Log.d("EYAD", "onResponse: " + response.body().getAlbumInfo().getMbid());
                Log.d("EYAD", "onResponse: " + response.body().getAlbumInfo().getName());
                Log.d("EYAD", "onResponse: " + response.body().getAlbumInfo().getImageUrl());
                Log.d("EYAD", "onResponse: " + response.body().getAlbumInfo().getCreatedAt());
                Log.d("EYAD", "onResponse: " + response.body().getAlbumInfo().getTracks().getTracksList().get(1).getName());

//                AsyncTask.execute(new Runnable() {
//                    @Override
//                    public void run() {
//                        AlbumDatabase.getAlbumDatabase(MainActivity.this).albumDao().insert(response.body().getAlbumInfo());
//                    }
//                });

            }

            @Override
            public void onFailure(Call<AlbumInfoResponse> call, Throwable t) {

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        mainPresenter.onResume();
    }

    @Override
    protected void onDestroy() {
        mainPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            startActivity(new Intent(MainActivity.this, SearchActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProgress() {
        // Show a progress bar in the middle of the screen to indicate that we are fetching the data
        storedAlbumsRecyclerView.setVisibility(View.GONE);
        infoMessage.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        // Hide the progress bar to indicate that the fetching process has finished
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showStoredAlbums(List<AlbumInfo> items) {
        // If the fetching process was successful then hide all other views and show the data
        infoMessage.setVisibility(View.GONE);

        storedAlbumsRecyclerView.setAdapter(new MainAdapter(items, this::onAlbumClicked));
        storedAlbumsRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMessage(String message) {
        // If the fetching process failed or there is no data then hide all other views and show a message to the user
        storedAlbumsRecyclerView.setVisibility(View.GONE);

        infoMessage.setText(message);
        infoMessage.setVisibility(View.VISIBLE);
    }

    public void onAlbumClicked(Object albumInfo) {
        // Open the details screen of the album
        Intent intent = new Intent(MainActivity.this, AlbumInfoActivity.class);
        intent.putExtra("album_info", ((AlbumInfo) albumInfo));
        startActivity(intent);
    }

}
