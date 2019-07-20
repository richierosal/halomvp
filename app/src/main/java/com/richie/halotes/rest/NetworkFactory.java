package com.richie.halotes.rest;


import android.content.Context;

import com.richie.halotes.BuildConfig;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkFactory {
 
//    public static final String BASE_URL = "http://mobayar.com/v1/";
    public static final String BASE_URL = "http://dev.mobayar.com/v1/";

    private static Retrofit retrofit = null;
 
    public static APIService getAPI(Context context) {
        if (retrofit == null) {

            OkHttpClient client = okHttpClient(context);

            retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL).client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(APIService.class);
    }

    public static OkHttpClient okHttpClient(Context context) {
        File cacheFile = new File(context.getCacheDir(), "responses");
        Cache cache = null;
        try {
            cache = new Cache(cacheFile, 10 * 1024 * 1024);
        } catch (Exception e) {
            e.printStackTrace();
        }

        OkHttpClient okHttpClient;
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(30, TimeUnit.SECONDS);
        builder.writeTimeout(30, TimeUnit.SECONDS);
        builder.cache(null);
        if(BuildConfig.DEBUG){
            builder.addInterceptor(interceptor);
        }

        builder.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                // Customize the request
                Request request = original.newBuilder()
                        .header("Content-Type", "application/json")
                        .build();

                okhttp3.Response response = chain.proceed(request);
                response.cacheResponse();
                // Customize or return the response
                return response;
            }
        });

        builder.cache(cache);


        okHttpClient = builder.build();

        return okHttpClient;
    }
}