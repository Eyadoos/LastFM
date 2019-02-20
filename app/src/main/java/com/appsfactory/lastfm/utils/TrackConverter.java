package com.appsfactory.lastfm.utils;

import com.appsfactory.lastfm.models.Tracks;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import androidx.room.TypeConverter;

/**
 * Created by Mhanna, Eyad on 17/02/2019.
 */

public class TrackConverter {

    private static Gson gson = new Gson();

    @TypeConverter
    public static Tracks stringToTracks(String data) {
        Type listType = new TypeToken<Tracks>() {
        }.getType();
        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String tracksToString(Tracks someObjects) {
        return gson.toJson(someObjects);
    }

}
