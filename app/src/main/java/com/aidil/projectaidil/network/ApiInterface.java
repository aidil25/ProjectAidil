package com.aidil.projectaidil.network;

import com.aidil.projectaidil.model.Movies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET(Constants.MOVIE_PATH + "/popular")
    Call<Movies> popular(@Query("page") int page);

    @GET(Constants.MOVIE_PATH + "/{movie_id}/similar")
    Call<Movies> similarMovies(
            @Query("page") int page);
}
