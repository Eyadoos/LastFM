package com.appsfactory.lastfm.dao;

import com.appsfactory.lastfm.models.AlbumInfo;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

/**
 * Created by Mhanna, Eyad on 16/02/2019.
 */

@Dao
public interface AlbumDao {

    @Query("SELECT * FROM Album ORDER BY created_at DESC")
    List<AlbumInfo> getAll();

    @Query("SELECT * from Album WHERE name = :albumName AND artist_name = :artistName")
    AlbumInfo getAlbumByName(String albumName, String artistName);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(AlbumInfo album);

    @Delete
    void delete(AlbumInfo album);

}
