package com.shira.movies.database.actiontv;

import com.shira.movies.models.Movie;
import com.shira.movies.models.Tv;

import java.util.List;

public interface FavoritesInterfaceTv {
    void favoritesFetched(List<Tv> tvFavorites);
}
