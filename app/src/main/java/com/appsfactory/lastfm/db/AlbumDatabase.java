package com.appsfactory.lastfm.db;

import android.content.Context;

import com.appsfactory.lastfm.dao.AlbumDao;
import com.appsfactory.lastfm.models.AlbumInfo;
import com.appsfactory.lastfm.utils.Constants;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * Created by Mhanna, Eyad on 16/02/2019.
 */

//TODO Change Export Schema Value to False when Shipping the APP
@Database(entities = {AlbumInfo.class}, version = 1, exportSchema = true)
public abstract class AlbumDatabase extends RoomDatabase {

    public abstract AlbumDao albumDao();

    private static AlbumDatabase DATABASE_INSTANCE;

    public static AlbumDatabase getAlbumDatabase(Context context) {
        if (DATABASE_INSTANCE == null) {
            DATABASE_INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AlbumDatabase.class,
                    Constants.DATABASE_NAME)
                            .build();
        }
        return DATABASE_INSTANCE;
    }

}
