package com.appsfactory.lastfm.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import androidx.room.ColumnInfo;

/**
 * Created by Mhanna, Eyad on 16/02/2019.
 */

public class AlbumWiki implements Serializable {

    @ColumnInfo(name = "time_published")
    @SerializedName("published")
    private String timePublished;
    @SerializedName("summary")
    private String summary;
    @SerializedName("content")
    private String content;

    public String getTimePublished() throws NullPointerException {
        return timePublished.split(",")[0];
    }

    public void setTimePublished(String timePublished) {
        this.timePublished = timePublished;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
