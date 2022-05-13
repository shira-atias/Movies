package com.shira.movies.ui.tv;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.shira.movies.api.MoviesManager;
import com.shira.movies.database.TvAppDatabase;
import com.shira.movies.database.TvDao;
import com.shira.movies.database.actiontv.FavoritesInterfaceTv;
import com.shira.movies.models.Tv;

import java.util.List;

public class TVViewModel extends AndroidViewModel {


    private final MutableLiveData<List<Tv>> tvRealityMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<Tv>> tvDramaMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<Tv>> tvComedyMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<Tv>> tvAnimationMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<Tv>> tvSoapMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<Tv>> tvFamilyMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<Tv>> tvDocumentaryMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<Tv>> tvMutableLiveData = new MutableLiveData<>();
    private MoviesManager manager = new MoviesManager();

    TvDao tvDao;

    public TVViewModel(@NonNull Application application) {
        super(application);
        tvDao = TvAppDatabase.getTvDao(getApplication());
    }

    public void addTv(Tv tv) {
        new Thread(() ->{
            tv.setFavorite(true);
            tvDao.insertAll(tv);
        }).start();
    }
    public void deleteTv(Tv tv) {
        new Thread(() ->{
            tvDao.delete(tv);
        }).start();
    }
    public void fetchFavorites(List<Tv> allTv, FavoritesInterfaceTv callBack) {
        new Thread(() -> {
            List<Tv> favorites = tvDao.getAllTvList();
            for(Tv tv : allTv) {
                for(Tv favorite : favorites) {
                    if(tv.getId() == favorite.getId()) {
                        tv.setFavorite(true);
                    }
                }
            }
            callBack.favoritesFetched(allTv);
        }).start();
    }

    public MutableLiveData<List<Tv>> getTvRealityMutableLiveData() {
        manager.getTvManager(tvRealityMutableLiveData,"10764");
        return tvRealityMutableLiveData;
    }

    public MutableLiveData<List<Tv>> getTvDramaMutableLiveData() {
        manager.getTvManager(tvDramaMutableLiveData,"18");
        return tvDramaMutableLiveData;
    }

    public MutableLiveData<List<Tv>> getTvComedyMutableLiveData() {
        manager.getTvManager(tvComedyMutableLiveData,"35");
        return tvComedyMutableLiveData;
    }

    public MutableLiveData<List<Tv>> getTvAnimationMutableLiveData() {
        manager.getTvManager(tvAnimationMutableLiveData,"16");
        return tvAnimationMutableLiveData;
    }

    public MutableLiveData<List<Tv>> getTvSoapMutableLiveData() {
        manager.getTvManager(tvSoapMutableLiveData,"10766");
        return tvSoapMutableLiveData;
    }

    public MutableLiveData<List<Tv>> getTvFamilyMutableLiveData() {
        manager.getTvManager(tvFamilyMutableLiveData,"10751");
        return tvFamilyMutableLiveData;
    }

    public MutableLiveData<List<Tv>> getTvDocumentaryMutableLiveData() {
        manager.getTvManager(tvDocumentaryMutableLiveData,"99");
        return tvDocumentaryMutableLiveData;
    }

    public MutableLiveData<List<Tv>> getTvMutableLiveData(String idGenre) {
        manager.getTvManager(tvMutableLiveData,idGenre);
        return tvMutableLiveData;
    }
}