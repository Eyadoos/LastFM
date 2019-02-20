package com.appsfactory.lastfm.network;

import com.appsfactory.lastfm.utils.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mhanna, Eyad on 16/02/2019.
 */

public class NetworkService {

    public static NetworkService instance = null;
    private LastFMClient lastFMClient;

    private NetworkService() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .writeTimeout(10000, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        lastFMClient = retrofit.create(LastFMClient.class);

    }

    public static NetworkService getInstance() {
        if (instance == null) {
            instance = new NetworkService();
        }
        return instance;
    }

    public static void reset() {
        instance = null;
    }

    public LastFMClient getClient() {
        return lastFMClient;
    }

}
