package com.shira.movies.database.actiontv;

import com.shira.movies.models.Movie;
import com.shira.movies.models.Tv;

public interface DatabaseTvListener {
    void didDeleteItem(Tv tv);
}
