package com.investrack.app.api;

import com.investrack.app.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiService {

    @GET
    Call<NewsResponse> fetchLatestNews(@Url String newsApiUrl);
}
