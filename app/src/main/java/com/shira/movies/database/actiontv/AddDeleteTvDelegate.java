package com.shira.movies.database.actiontv;


import com.shira.movies.models.Tv;

public interface AddDeleteTvDelegate {
    void addTvToFavorites(Tv tv);
    void deleteTvFromFavorites(Tv tv);
}
