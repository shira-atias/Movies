package com.shira.movies.api;

import com.shira.movies.models.Movie;
import com.shira.movies.models.MovieDetails;
import com.shira.movies.models.MoviesResponse;
import com.shira.movies.models.Tv;
import com.shira.movies.models.TvDetails;
import com.shira.movies.models.TvResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MoviesSrvice {
    @GET("discover/movie")
    Call<MoviesResponse> getMovies(@Query("api_key") String apiKey,
                                   @Query("language") String language,
                                   @Query("with_genres") String idGenre);


    @GET("discover/tv")
    Call<TvResponse> getTv(@Query("api_key") String apiKey,
                           @Query("language") String language,
                           @Query("with_genres") String idGenre);


    @GET("movie/{movie_id}")
    Call<MovieDetails> getDetailsMovie(@Path("movie_id") String idMovie,
                                       @Query("api_key") String apiKey,
                                       @Query("language") String language);

    @GET("tv/{movie_id}")
    Call<TvDetails> getDetailsTv(@Path("movie_id") String idMovie,
                                 @Query("api_key") String apiKey,
                                 @Query("language") String language);


}
