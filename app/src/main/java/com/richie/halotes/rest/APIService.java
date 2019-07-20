package com.richie.halotes.rest;

import com.google.gson.JsonObject;
import com.richie.halotes.model.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    // ==================== SEARCH ====================
    @GET("search")
    Call<News> search(@Query("query") String query,
                      @Query("page") int page);

}