package com.appsfactory.lastfm.utils;

import com.appsfactory.lastfm.models.ImageItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import androidx.room.TypeConverter;

/**
 * Created by Mhanna, Eyad on 16/02/2019.
 */

public class ImageConverter {

    private static Gson gson = new Gson();

    @TypeConverter
    public static List<ImageItem> stringToImageItemList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<ImageItem>>() {}.getType();
        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String imageItemListToString(List<ImageItem> someObjects) {
        return gson.toJson(someObjects);
    }

}
