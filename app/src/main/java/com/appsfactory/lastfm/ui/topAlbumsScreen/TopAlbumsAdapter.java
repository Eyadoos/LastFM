package com.appsfactory.lastfm.ui.topAlbumsScreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.appsfactory.lastfm.R;
import com.appsfactory.lastfm.models.Album;
import com.appsfactory.lastfm.utils.ListItemClickListener;
import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mhanna, Eyad on 19/02/2019.
 */

public class TopAlbumsAdapter extends RecyclerView.Adapter<TopAlbumsAdapter.AlbumItemViewHolder> {

    private List<Album> data;
    private ListItemClickListener listItemClickListener;

    public TopAlbumsAdapter(List<Album> data, ListItemClickListener listItemClickListener) {
        this.data = data;
        this.listItemClickListener = listItemClickListener;
    }

    @NonNull
    @Override
    public AlbumItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view holder
        View inflatedView = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.top_album_item_layout, parent, false);
        AlbumItemViewHolder viewHolder = new AlbumItemViewHolder(inflatedView, parent.getContext());
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumItemViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class AlbumItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.album_image_view)
        ImageView albumImageView;
        @BindView(R.id.album_name_text_view)
        TextView albumNameTextView;
        @BindView(R.id.album_play_count_text_view)
        TextView albumPlayCountTextView;
        @BindView(R.id.item_parent_layout)
        CardView itemParentLayout;

        private Context context;

        public AlbumItemViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            this.context = context;
            ButterKnife.bind(this, itemView);
        }

        public void bind(Album album) {
            albumNameTextView.setText(album.getName());
            albumPlayCountTextView.setText(album.getPlaycount());
            itemParentLayout.setOnClickListener(v -> listItemClickListener.onItemClicked(album));
            if (!album.getImageUrl().equalsIgnoreCase(""))
                Glide.with(context).load(album.getImageUrl()).into(albumImageView);
        }

    }

}