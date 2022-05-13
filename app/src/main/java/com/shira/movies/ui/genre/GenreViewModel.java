package com.shira.movies.ui.genre;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.shira.movies.api.MoviesManager;
import com.shira.movies.models.Movie;

import java.util.List;

public class GenreViewModel extends ViewModel {

    private final MutableLiveData<List<Movie>> genreMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<Throwable> excMutableLiveData = new MutableLiveData<>();
    private MoviesManager manager = new MoviesManager();

    public GenreViewModel() {
    }

}