package com.shira.movies.ui.details;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.shira.movies.api.MoviesManager;
import com.shira.movies.models.MovieDetails;
import com.shira.movies.models.TvDetails;

public class DetailsViewModel extends ViewModel {
    private final MutableLiveData<MovieDetails> moviesLiveData = new MutableLiveData<>();
    private final MutableLiveData<TvDetails> tvLiveData = new MutableLiveData<>();
    MoviesManager manager = new MoviesManager();
    public DetailsViewModel() {
    }

    public MutableLiveData<MovieDetails> getMoviesLiveData() {
        return moviesLiveData;
    }

    public void getMovieDetails(String id) {
        manager.getMovieDetail(moviesLiveData,id);
    }

    public MutableLiveData<TvDetails> getTvLiveData(String idTV) {
        manager.getTvDetail(tvLiveData,idTV);
        return tvLiveData;
    }
}