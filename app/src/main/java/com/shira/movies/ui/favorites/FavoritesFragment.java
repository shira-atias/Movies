package com.shira.movies.ui.favorites;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shira.movies.R;
import com.shira.movies.adapter.CategoryMovieAdapter;
import com.shira.movies.adapter.CategoryTVAdapter;
import com.shira.movies.adapter.FavoritesMovieAdapter;
import com.shira.movies.adapter.FavoritesTvAdapter;
import com.shira.movies.adapter.MovieAdapter;
import com.shira.movies.adapter.TvAdapter;
import com.shira.movies.database.actionmovie.FavoritesDeleteMovieDelegate;
import com.shira.movies.database.actiontv.FavoriteDeleteTvDelegate;
import com.shira.movies.databinding.FragmentFavoritesBinding;
import com.shira.movies.models.Movie;
import com.shira.movies.models.Tv;


public class FavoritesFragment extends Fragment{

    private FavoritesViewModel favoritesViewModel;
    private FragmentFavoritesBinding binding;




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        favoritesViewModel =
                new ViewModelProvider(this).get(FavoritesViewModel.class);

        binding = FragmentFavoritesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        favoritesViewModel.getAllMovie().observe(getViewLifecycleOwner(),movies -> {
            if (movies.size() == 0){
                binding.tvNameMovieF.setText("Not saved");

            }
            RecyclerView recyclerView = binding.rvFavoriteMovie;
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
            recyclerView.setAdapter(new FavoritesMovieAdapter(movies));
        });

        favoritesViewModel.getAllTv().observe(getViewLifecycleOwner(),tvs -> {
            if (tvs.size() == 0){
                binding.tvNameTvF.setText("Not saved");
            }

            RecyclerView recyclerView = binding.rvFavoriteTv;
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
            recyclerView.setAdapter(new FavoritesTvAdapter(tvs));
        });
        binding.tvNameMovieF.setOnClickListener(v -> {
            Bundle arg = new Bundle();
            arg.putString("id","31");
            arg.putString("fragment","favorite_movie");
            Navigation.findNavController(v).navigate(R.id.action_navigation_favorites_to_genreFragment,arg);
        });
        binding.tvNameTvF.setOnClickListener(v -> {
            Bundle arg = new Bundle();
            arg.putString("id","11");
            arg.putString("fragment","favorite_tv");
            Navigation.findNavController(v).navigate(R.id.action_navigation_favorites_to_genreFragment,arg);
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}