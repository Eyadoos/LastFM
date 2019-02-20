package com.appsfactory.lastfm.ui.searchScreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.appsfactory.lastfm.R;
import com.appsfactory.lastfm.models.Artist;
import com.appsfactory.lastfm.utils.ListItemClickListener;
import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mhanna, Eyad on 17/02/2019.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ArtistItemViewHolder> {

    private List<Artist> data;
    private ListItemClickListener listItemClickListener;

    public SearchAdapter(List<Artist> data, ListItemClickListener listItemClickListener) {
        this.data = data;
        this.listItemClickListener = listItemClickListener;
    }

    @NonNull
    @Override
    public ArtistItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view holder
        View inflatedView = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.artist_item_layout, parent, false);
        ArtistItemViewHolder viewHolder = new ArtistItemViewHolder(inflatedView, parent.getContext());
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistItemViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ArtistItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.artist_image_view)
        ImageView artistImageView;
        @BindView(R.id.artist_name_text_view)
        TextView artistNameTextView;
        @BindView(R.id.artist_listeners_count_text_view)
        TextView artistListenersCountTextView;
        @BindView(R.id.artist_streamable_text_view)
        TextView artistStreamableTextView;
        @BindView(R.id.item_parent_layout)
        CardView itemParentLayout;

        private Context context;

        public ArtistItemViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            this.context = context;
            ButterKnife.bind(this, itemView);
        }

        public void bind(Artist artist) {
            artistNameTextView.setText(artist.getName());
            artistListenersCountTextView.setText(artist.getListenersCount());
            artistStreamableTextView.setText(artist.getStreamable());
            itemParentLayout.setOnClickListener(v -> listItemClickListener.onItemClicked(artist));
            if (!artist.getImageUrl().equalsIgnoreCase(""))
                Glide.with(context).load(artist.getImageUrl()).into(artistImageView);
        }

    }

}
