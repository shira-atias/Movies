package com.shira.movies.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.shira.movies.models.Movie;
import com.shira.movies.models.MovieDetails;

import java.util.List;

@Dao
public interface MovieDao {
    @Query("SELECT * FROM movies")
    LiveData<List<Movie>> getAll();


    @Query("SELECT * FROM movies")
    List<Movie> getAllMoviesList();

    @Query("SELECT * FROM movies WHERE id IN (:idMovie)")
    LiveData<Movie> loadById(int idMovie);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Movie... movie);

    @Delete
    void delete(Movie movie);
}
