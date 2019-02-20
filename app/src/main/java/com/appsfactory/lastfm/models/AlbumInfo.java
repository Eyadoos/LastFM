package com.appsfactory.lastfm.models;

import com.appsfactory.lastfm.utils.Constants;
import com.appsfactory.lastfm.utils.ImageConverter;
import com.appsfactory.lastfm.utils.TagConverter;
import com.appsfactory.lastfm.utils.TimestampConverter;
import com.appsfactory.lastfm.utils.TrackConverter;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

/**
 * Created by Mhanna, Eyad on 16/02/2019.
 */

@Entity(tableName = "Album")
public class AlbumInfo implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @SerializedName("mbid")
    private String mbid;
    @SerializedName("name")
    private String name;
    @ColumnInfo(name = "artist_name")
    @SerializedName("artist")
    private String artistName;
    @SerializedName("url")
    private String url;
    @SerializedName("image")
    @TypeConverters({ImageConverter.class})
    private List<ImageItem> images;
    @ColumnInfo(name = "listeners_count")
    @SerializedName("listeners")
    private String listenersCount;
    @ColumnInfo(name = "play_count")
    @SerializedName("playcount")
    private String playCount;
    @SerializedName("tracks")
    @TypeConverters({TrackConverter.class})
    private Tracks tracks;
    @SerializedName("tags")
    @TypeConverters({TagConverter.class})
    private Tags tags;
    @Nullable
    @Embedded
    @SerializedName("wiki")
    private AlbumWiki wiki;
    @ColumnInfo(name = "created_at")
    @TypeConverters({TimestampConverter.class})
    private Date createdAt;

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFormattedListenersCount() {
        return String.format(Locale.US, "%,d", Integer.parseInt(listenersCount)) + " listeners, ";
    }

    public String getListenersCount() {
        return listenersCount;
    }

    public void setListenersCount(String listenersCount) {
        this.listenersCount = listenersCount;
    }

    public String getFormattedPlayCount() {
        return "played " + String.format(Locale.US, "%,d", Integer.parseInt(playCount)) + " times";
    }

    public String getPlayCount() {
        return playCount;
    }

    public void setPlayCount(String playCount) {
        this.playCount = playCount;
    }

    public AlbumWiki getWiki() throws NullPointerException {
        return wiki;
    }

    public void setWiki(AlbumWiki wiki) {
        this.wiki = wiki;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<ImageItem> getImages() {
        return images;
    }

    public void setImages(List<ImageItem> images) {
        this.images = images;
    }

    public String getImageUrl() {
        if (getImages() != null) {
            if (getImages().size() > 0) {
                for (ImageItem img : getImages()) {
                    if (img.getSize().equalsIgnoreCase(Constants.IMAGE_SIZE)) {
                        return img.getUrl();
                    }
                }
            }
        }
        return null;
    }

    public Tracks getTracks() {
        return tracks;
    }

    public void setTracks(Tracks tracks) {
        this.tracks = tracks;
    }

    public ArrayList<String> getAlbumTags() {
        ArrayList<String> tags = new ArrayList<>();
        for (Tag tag : getTags().getTagsList()) {
            tags.add(tag.getName());
        }
        return tags;
    }

    public Tags getTags() {
        return tags;
    }

    public void setTags(Tags tags) {
        this.tags = tags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
