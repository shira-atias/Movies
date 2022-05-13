package com.shira.movies.ui.movies;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.shira.movies.database.MovieAppDatabase;
import com.shira.movies.database.actionmovie.FavoritesInterfaceMovie;
import com.shira.movies.api.MoviesManager;
import com.shira.movies.database.MovieDao;
import com.shira.movies.models.Movie;

import java.util.List;

public class MoviesViewModel extends AndroidViewModel {

    private final MutableLiveData<List<Movie>> moviesActinLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<Movie>> moviesDramaLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<Movie>> moviesAnimationLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<Movie>> moviesComedyLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<Movie>> moviesScienceFictionLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<Movie>> moviesFamilyLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<Movie>> moviesHorrorLiveData = new MutableLiveData<>();

    private final MutableLiveData<List<Movie>> moviesLiveData = new MutableLiveData<>();

    private final MutableLiveData<List<Movie>> mutableGenerLiveData = new MutableLiveData<>();
    private final MutableLiveData<Throwable> excLiveData = new MutableLiveData<>();

    MoviesManager manager = new MoviesManager();
    MovieDao movieDao;


    public MoviesViewModel(Application application) {
        super(application);
        movieDao = MovieAppDatabase.getMovieDao(getApplication());
    }

    public void addMovie(Movie movie) {
        new Thread(() ->{
            movie.setIsFavorite(true);
            movieDao.insertAll(movie);
        }).start();
    }

    public void deleteMovie(Movie movie) {
        new Thread(() ->{
            movieDao.delete(movie);
        }).start();
    }


    public void fetchFavorites(List<Movie> allMovies, FavoritesInterfaceMovie callBack) {
        new Thread(() -> {
            List<Movie> favorites = movieDao.getAllMoviesList();
            for(Movie movie : allMovies) {
                for(Movie next : favorites) {
                    if(movie.getId() == next.getId()) {
                        movie.setIsFavorite(true);
                    }
                }
            }
            callBack.favoritesFetched(allMovies);
        }).start();
    }



    public MutableLiveData<List<Movie>> getMoviesActinLiveData() {
        manager.getMovieManager(moviesActinLiveData,"28");
        return moviesActinLiveData;
    }

    public MutableLiveData<List<Movie>> getMoviesDramaLiveData() {
        manager.getMovieManager(moviesDramaLiveData, "18");
        return moviesDramaLiveData;
    }

    public MutableLiveData<List<Movie>> getMoviesAnimationLiveData() {
        manager.getMovieManager(moviesAnimationLiveData, "16");
        return moviesAnimationLiveData;
    }

    public MutableLiveData<List<Movie>> getMoviesComedyLiveData() {
        manager.getMovieManager(moviesComedyLiveData, "35");
        return moviesComedyLiveData;
    }

    public MutableLiveData<List<Movie>> getMoviesScienceFictionLiveData() {
        manager.getMovieManager(moviesScienceFictionLiveData, "878");
        return moviesScienceFictionLiveData;
    }

    public MutableLiveData<List<Movie>> getMoviesFamilyLiveData() {
        manager.getMovieManager(moviesFamilyLiveData, "10751");
        return moviesFamilyLiveData;
    }

    public MutableLiveData<List<Movie>> getMoviesHorrorLiveData() {
        manager.getMovieManager(moviesHorrorLiveData, "27");
        return moviesHorrorLiveData;
    }

    public MutableLiveData<List<Movie>> getMoviesLiveData(String id) {
        manager.getMovieManager(moviesLiveData,id);
        return moviesLiveData;
    }


    public MutableLiveData<Throwable> getExcLiveData() {
        return excLiveData;
    }
}