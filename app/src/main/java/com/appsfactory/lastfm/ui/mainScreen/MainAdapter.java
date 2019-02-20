package com.appsfactory.lastfm.ui.mainScreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.appsfactory.lastfm.R;
import com.appsfactory.lastfm.models.AlbumInfo;
import com.appsfactory.lastfm.utils.ListItemClickListener;
import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mhanna, Eyad on 16/02/2019.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.AlbumItemViewHolder> {

    private List<AlbumInfo> data;
    private ListItemClickListener listItemClickListener;

    MainAdapter(List<AlbumInfo> data, ListItemClickListener listItemClickListener) {
        this.data = data;
        this.listItemClickListener = listItemClickListener;
    }

    @NonNull
    @Override
    public AlbumItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view holder
        View inflatedView = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_item_layout, parent, false);
        AlbumItemViewHolder viewHolder = new AlbumItemViewHolder(inflatedView, parent.getContext());
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumItemViewHolder holder, int position) {
        // Bind the data to the view holder
        holder.bind(getData().get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class AlbumItemViewHolder extends RecyclerView.ViewHolder {

        private Context context;

        @BindView(R.id.item_parent_layout)
        CardView itemParentLayout;
        @BindView(R.id.album_image_view)
        ImageView albumImageView;
        @BindView(R.id.album_name_text_view)
        TextView albumNameTextView;
        @BindView(R.id.album_artist_text_view)
        TextView albumArtistTextView;

        public AlbumItemViewHolder(View itemView, Context context) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.context = context;
        }

        public void bind(AlbumInfo albumInfo) {
            albumNameTextView.setText(albumInfo.getName());
            albumArtistTextView.setText(albumInfo.getArtistName());
            itemParentLayout.setOnClickListener(v -> listItemClickListener.onItemClicked(albumInfo));
            Glide.with(context).load(albumInfo.getImageUrl()).into(albumImageView);
        }

    }

    private List<AlbumInfo> getData() {
        return data;
    }

    public void setData(List<AlbumInfo> data) {
        this.data = data;
    }

}
