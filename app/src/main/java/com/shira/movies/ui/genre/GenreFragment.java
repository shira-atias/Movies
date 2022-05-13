package com.shira.movies.ui.genre;

import androidx.activity.OnBackPressedCallback;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.shira.movies.R;
import com.shira.movies.adapter.MovieAdapter;
import com.shira.movies.database.actionmovie.AddDeleteMovieDelegate;
import com.shira.movies.adapter.TvAdapter;
import com.shira.movies.database.actionmovie.FavoritesDeleteMovieDelegate;
import com.shira.movies.database.actiontv.AddDeleteTvDelegate;
import com.shira.movies.database.actiontv.FavoriteDeleteTvDelegate;
import com.shira.movies.databinding.GenreFragmentBinding;
import com.shira.movies.models.Movie;
import com.shira.movies.models.Tv;
import com.shira.movies.ui.favorites.FavoritesViewModel;
import com.shira.movies.ui.movies.MoviesViewModel;
import com.shira.movies.ui.tv.TVViewModel;

public class GenreFragment extends Fragment  implements AddDeleteMovieDelegate, AddDeleteTvDelegate  {


    private MoviesViewModel moviesViewModel;
    private TVViewModel tvViewModel;
    private FavoritesViewModel favoritesViewModel;
    private MovieAdapter movieAdapter;
    private TvAdapter tvAdapter;
    private GenreFragmentBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        moviesViewModel = new ViewModelProvider(this).get(MoviesViewModel.class);
        tvViewModel = new ViewModelProvider(this).get(TVViewModel.class);
        favoritesViewModel = new ViewModelProvider(this).get(FavoritesViewModel.class);


        binding = GenreFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Bundle bag = getArguments();
       String idGenre = bag.getString("id");
       String fragment = bag.getString("fragment");

       if (fragment.equals("movie")){
           moviesViewModel.getMoviesLiveData(idGenre).observe(getViewLifecycleOwner(),movies -> {

               moviesViewModel.fetchFavorites(movies, favorites -> {

                   // not in the main
                   if(getActivity() != null)
                   getActivity().runOnUiThread(() -> {
                       RecyclerView recyclerView = root.findViewById(R.id.rvGenre);
                       recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                       recyclerView.setAdapter(new MovieAdapter(favorites,GenreFragment.this));
                   });

               });

           });
       }else if (fragment.equals("Tv")){
           tvViewModel.getTvMutableLiveData(idGenre).observe(getViewLifecycleOwner(),tvs -> {
               tvViewModel.fetchFavorites(tvs,tvFavorites -> {
                   if (getActivity() != null)
                       getActivity().runOnUiThread(() ->{
                           RecyclerView recyclerView = root.findViewById(R.id.rvGenre);
                           recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                           recyclerView.setAdapter(new TvAdapter(tvFavorites,GenreFragment.this));

                       });
               });
           });

       }else if (fragment.equals("favorite_movie")){
           favoritesViewModel.getAllMovie().observe(getViewLifecycleOwner(),movies -> {
               RecyclerView recyclerView = root.findViewById(R.id.rvGenre);
               recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
               movieAdapter = new MovieAdapter(movies, new FavoritesDeleteMovieDelegate() {
                   @Override
                   public void deleteFromFavoritesF(Movie m) {
                       favoritesViewModel.deleteMovie(m, m1 -> {
                           getActivity().runOnUiThread(() -> movieAdapter.notifyDataSetChanged());
                       });
                   }
               });
               recyclerView.setAdapter(movieAdapter);
           });

       }else if (fragment.equals("favorite_tv")){
           favoritesViewModel.getAllTv().observe(getViewLifecycleOwner(),tvs -> {
               RecyclerView recyclerView = binding.rvGenre;
               recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
               tvAdapter = new TvAdapter(tvs, new FavoriteDeleteTvDelegate() {
                   @Override
                   public void deleteFromFavorites(Tv tv) {
                       favoritesViewModel.deleteTv(tv,tv1 -> {
                           getActivity().runOnUiThread(()-> tvAdapter.notifyDataSetChanged());
                       });
                   }
               });
               recyclerView.setAdapter(tvAdapter);
           });
       }

        final TextView tvGendreName = binding.tvGenreName;
        SendText(idGenre,tvGendreName);


        return root;
    }


    private void SendText(String idGenre, TextView textView){
        switch (idGenre) {
            case "28":
                textView.setText("Action");
                break;
            case "18":
                textView.setText("Drama");
                break;
            case "16":
                textView.setText("Animation");
                break;
            case "35":
                textView.setText("Comedy");
                break;
            case "878":
                textView.setText("Science Fiction");
                break;
            case "10751":
                textView.setText("Family");
                break;
            case "27":
                textView.setText("Horror");
                break;
            case "10764":
                textView.setText("Reality");
                break;
            case "10766":
                textView.setText("Soap");
                break;
            case "99":
                textView.setText("Documentary");
                break;
            case "31":
                textView.setText("Favorite Movie");
                break;
            case "11":
                textView.setText("Favorite Tv");
                break;
        }

    }



    @Override
    public void addMovieToFavorites(Movie m) {
        moviesViewModel.addMovie(m);
    }

    @Override
    public void deleteFromFavorites(Movie m) {
        moviesViewModel.deleteMovie(m);
    }

    @Override
    public void addTvToFavorites(Tv tv) {
        tvViewModel.addTv(tv);
    }

    @Override
    public void deleteTvFromFavorites(Tv tv) {
        tvViewModel.deleteTv(tv);
    }

}
