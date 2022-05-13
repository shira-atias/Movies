package com.shira.movies.database.actionmovie;

import com.shira.movies.models.Movie;

public interface DatabaseMovieListener {

    void didDeleteItem(Movie m);
}
