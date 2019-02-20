package com.appsfactory.lastfm.utils;

import com.appsfactory.lastfm.models.Tags;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import androidx.room.TypeConverter;

/**
 * Created by Mhanna, Eyad on 17/02/2019.
 */

public class TagConverter {

    private static Gson gson = new Gson();

    @TypeConverter
    public static Tags stringToTags(String data) {
        Type listType = new TypeToken<Tags>() {}.getType();
        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String tagsToString(Tags someObjects) {
        return gson.toJson(someObjects);
    }

}
