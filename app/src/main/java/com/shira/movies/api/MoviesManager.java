package com.shira.movies.api;

import androidx.lifecycle.MutableLiveData;

import com.shira.movies.models.Movie;
import com.shira.movies.models.MovieDetails;
import com.shira.movies.models.MoviesResponse;
import com.shira.movies.models.Tv;
import com.shira.movies.models.TvDetails;
import com.shira.movies.models.TvResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesManager {
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    private MoviesSrvice service = retrofit.create(MoviesSrvice.class);
    private static final String API_KEY = "bb9f115c0ea754cfbd4efa8c932ab511";
    private static final String LANGUAGE = "";
//    private static final String LANGUAGE = "he-IL";



    public void getTvManager(MutableLiveData<List<Tv>> tvMutablelLiveData, String idGenre){
        Call<TvResponse> responseCall= service.getTv(API_KEY,LANGUAGE,idGenre);
        responseCall.enqueue(new Callback<TvResponse>() {
            @Override
            public void onResponse(Call<TvResponse> call, Response<TvResponse> response) {
                TvResponse tvResponse= response.body();
                if (tvResponse != null){
                    List<Tv> tvs = tvResponse.getTvs();
                    tvMutablelLiveData.postValue(tvs);
                }
            }

            @Override
            public void onFailure(Call<TvResponse> call, Throwable t) {
                System.out.println(t);
            }
        });
    }


    public void getMovieManager(MutableLiveData<List<Movie>> movieLiveData , String idGenre){
        Call<MoviesResponse> responseCall = service.getMovies(API_KEY,LANGUAGE,idGenre);
        responseCall.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                MoviesResponse moviesResponse = response.body();
                if (moviesResponse != null){
                    List<Movie> movies = moviesResponse.getMovies();
                    movieLiveData.postValue(movies);
                }
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                System.out.println(t);
            }
        });
    }


    public void getMovieDetail(MutableLiveData<MovieDetails> movieDetailsMutableLiveData, String idMovies){
        Call<MovieDetails> detailsCall = service.getDetailsMovie(idMovies,API_KEY,LANGUAGE);
        detailsCall.enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
                MovieDetails movieDetails = response.body();
                if (movieDetails != null){
                    movieDetailsMutableLiveData.postValue(movieDetails);
                }
            }

            @Override
            public void onFailure(Call<MovieDetails> call, Throwable t) {
                System.out.println(t);
            }
        });
    }


    public void getTvDetail(MutableLiveData<TvDetails> tvMutableLiveData, String idTv){
        Call<TvDetails> responseCall = service.getDetailsTv(idTv,API_KEY,LANGUAGE);
        responseCall.enqueue(new Callback<TvDetails>() {
            @Override
            public void onResponse(Call<TvDetails> call, Response<TvDetails> response) {
                TvDetails tvDetails = response.body();
                if (tvDetails != null){
                    tvMutableLiveData.postValue(tvDetails);
                }
            }

            @Override
            public void onFailure(Call<TvDetails> call, Throwable t) {
                System.out.println(t);
            }
        });
    }



}
