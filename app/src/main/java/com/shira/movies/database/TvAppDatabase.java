package com.shira.movies.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.shira.movies.models.Tv;

@Database(entities = {Tv.class}, version = 1, exportSchema = false)
public abstract class TvAppDatabase extends RoomDatabase {

    public abstract TvDao tvDao();

    public static TvDao getTvDao(Context context){
        TvAppDatabase db = Room.databaseBuilder(context, TvAppDatabase.class,"database-tv").build();
        return db.tvDao();
    }


}
