package com.shira.movies.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.shira.movies.models.Movie;
import com.shira.movies.models.Tv;

import java.util.List;
@Dao
public interface TvDao {
    @Query("SELECT * FROM tv")
    LiveData<List<Tv>> getAll();


    @Query("SELECT * FROM tv")
    List<Tv> getAllTvList();

    @Query("SELECT * FROM tv WHERE id IN (:idTv)")
    LiveData<Tv> loadById(int idTv);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Tv... tvs);

    @Delete
    void delete(Tv tv);
}
