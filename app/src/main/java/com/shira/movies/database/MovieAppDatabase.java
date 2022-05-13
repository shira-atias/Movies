package com.shira.movies.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.shira.movies.models.Movie;

@Database(entities = {Movie.class}, version = 1, exportSchema = false)
public abstract class MovieAppDatabase extends RoomDatabase {

    public abstract MovieDao movieDao();

    public static MovieDao getMovieDao(Context context){
        MovieAppDatabase db = Room.databaseBuilder(context, MovieAppDatabase.class,"database-movie").build();
        return db.movieDao();
    }

}
