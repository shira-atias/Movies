package com.shira.movies.ui.favorites;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.shira.movies.database.MovieAppDatabase;
import com.shira.movies.database.MovieDao;
import com.shira.movies.database.TvAppDatabase;
import com.shira.movies.database.TvDao;
import com.shira.movies.database.actionmovie.DatabaseMovieListener;
import com.shira.movies.database.actiontv.DatabaseTvListener;
import com.shira.movies.models.Movie;
import com.shira.movies.models.Tv;

import java.util.List;

public class FavoritesViewModel extends AndroidViewModel {

    private MutableLiveData<String> favoritesLiveData;
    MovieDao movieDao;
    TvDao tvDao;

    public FavoritesViewModel(@NonNull Application application) {
        super(application);
         movieDao = MovieAppDatabase.getMovieDao(getApplication());
         tvDao = TvAppDatabase.getTvDao(getApplication());
    }
//movie
    public void deleteMovie(Movie movie, DatabaseMovieListener listener){
        new Thread(() ->{
            movieDao.delete(movie);
            listener.didDeleteItem(movie);
        }).start();
    }

    public LiveData<List<Movie>> getAllMovie(){
        return MovieAppDatabase.getMovieDao(getApplication()).getAll();
    }
//tv
    public void deleteTv(Tv tv, DatabaseTvListener listener){
        new Thread(() ->{
            tvDao.delete(tv);
            listener.didDeleteItem(tv);
        }).start();
    }
    public LiveData<List<Tv>> getAllTv(){

        return TvAppDatabase.getTvDao(getApplication()).getAll();
    }

}