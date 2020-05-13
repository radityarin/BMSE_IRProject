package com.example.booleanmodelsearchengine_irproject.api;

import com.example.booleanmodelsearchengine_irproject.response.NewsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MyApi {

    //Get all news
    @GET("news/all")
    Call<NewsResponse> getAllNews();

    //Get news with query
    @GET("news")
    Call<NewsResponse> getNewsWithQuery(
            @Query(value = "query",encoded = true) String query
    );

}
