package com.meeran.techfeed;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApiService {
    
    @GET("v2/top-headlines")
    Call<NewsResponse> getTopHeadlines(
            @Query("category") String category,
            @Query("country") String country,
            @Query("pageSize") int pageSize,
            @Query("page") int page,
            @Query("apiKey") String apiKey
    );
    
    @GET("v2/everything")
    Call<NewsResponse> searchNews(
            @Query("q") String query,
            @Query("sortBy") String sortBy,
            @Query("pageSize") int pageSize,
            @Query("page") int page,
            @Query("apiKey") String apiKey
    );
    
    @GET("v2/top-headlines")
    Call<NewsResponse> getTopHeadlinesByCountry(
            @Query("country") String country,
            @Query("pageSize") int pageSize,
            @Query("page") int page,
            @Query("apiKey") String apiKey
    );
}
