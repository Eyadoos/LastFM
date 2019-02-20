package com.appsfactory.lastfm.network;

import com.appsfactory.lastfm.models.AlbumInfoResponse;
import com.appsfactory.lastfm.models.SearchForArtistResponse;
import com.appsfactory.lastfm.models.TopAlbumsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Mhanna, Eyad on 16/02/2019.
 */

public interface LastFMClient {

    @GET("?method=artist.gettopalbums&format=json")
    Call<TopAlbumsResponse> topAlbumsForArtist(
            @Query("api_key") String apiKey,
            @Query("artist") String artistName
    );

    @GET("?method=artist.search&format=json")
    Call<SearchForArtistResponse> searchForArtist(
            @Query("api_key") String apiKey,
            @Query("artist") String searchTerm
    );

    @GET("?method=album.getinfo&format=json")
    Call<AlbumInfoResponse> getAlbumInfo(
            @Query("api_key") String apiKey,
            @Query("artist") String artistName,
            @Query("album") String album
    );

}
