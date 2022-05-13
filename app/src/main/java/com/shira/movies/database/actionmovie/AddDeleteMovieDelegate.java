package com.shira.movies.database.actionmovie;

import com.shira.movies.models.Movie;

public interface AddDeleteMovieDelegate {
    void addMovieToFavorites(Movie m);
    void deleteFromFavorites(Movie m);
}
