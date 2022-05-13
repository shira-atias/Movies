package com.shira.movies.database.actionmovie;

import com.shira.movies.models.Movie;

public interface FavoritesDeleteMovieDelegate {
    void deleteFromFavoritesF(Movie m);
}
