package com.appsfactory.lastfm.ui.albumDetailsScreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.appsfactory.lastfm.R;
import com.appsfactory.lastfm.models.Track;
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

public class AlbumTracksAdapter extends RecyclerView.Adapter<AlbumTracksAdapter.TrackItemViewHolder> {

    private List<Track> data;
    private String trackImage;

    public AlbumTracksAdapter(List<Track> data, String trackImage) {
        this.data = data;
        this.trackImage = trackImage;
    }

    @NonNull
    @Override
    public TrackItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view holder
        View inflatedView = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.track_item_layout, parent, false);
        TrackItemViewHolder viewHolder = new TrackItemViewHolder(inflatedView, parent.getContext());
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TrackItemViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class TrackItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.track_image_view)
        ImageView trackImageView;
        @BindView(R.id.track_name_text_view)
        TextView trackNameTextView;
        @BindView(R.id.track_duration_text_view)
        TextView trackDurationTextView;
        @BindView(R.id.item_parent_layout)
        CardView itemParentLayout;

        private Context context;

        public TrackItemViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            this.context = context;
            ButterKnife.bind(this, itemView);
        }

        public void bind(Track track) {
            trackNameTextView.setText(track.getName());
            trackDurationTextView.setText(track.getDuration());
            if (trackImage != null) Glide.with(context).load(trackImage).into(trackImageView);
        }

    }

}

