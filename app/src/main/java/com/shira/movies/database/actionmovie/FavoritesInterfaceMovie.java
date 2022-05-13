package com.shira.movies.database.actionmovie;

import com.shira.movies.models.Movie;

import java.util.List;

public interface FavoritesInterfaceMovie {

    void favoritesFetched(List<Movie> favorites);
}
