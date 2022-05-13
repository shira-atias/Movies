package com.shira.movies.ui.movies;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.shira.movies.R;
import com.shira.movies.adapter.CategoryMovieAdapter;
import com.shira.movies.databinding.FragmentMoviesBinding;
import com.shira.movies.models.Movie;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MoviesFragment extends Fragment {

    private MoviesViewModel moviesViewModel;
    private FragmentMoviesBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentMoviesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        binding.tvMAction.setOnClickListener(v -> GoToGenreFragment("28",v));
        binding.tvMDrama.setOnClickListener(v -> GoToGenreFragment("18",v));
        binding.tvMAnimation.setOnClickListener(v -> GoToGenreFragment("16",v));
        binding.tvMComedy.setOnClickListener(v -> GoToGenreFragment("35",v));
        binding.tvMScienceFiction.setOnClickListener(v -> GoToGenreFragment("878",v));
        binding.tvMFamily.setOnClickListener(v -> GoToGenreFragment("10751",v));
        binding.tvMHorror.setOnClickListener(v -> GoToGenreFragment("27",v));

        moviesViewModel =
                new ViewModelProvider(this).get(MoviesViewModel.class);

        moviesViewModel.getMoviesActinLiveData().observe(getViewLifecycleOwner(),movies -> {
            getRecyclerView(root, R.id.rvMAction,movies);
        });

        moviesViewModel.getMoviesDramaLiveData().observe(getViewLifecycleOwner(),movies -> {
            getRecyclerView(root,R.id.rvMDrama,movies);
        });
        moviesViewModel.getMoviesAnimationLiveData().observe(getViewLifecycleOwner(),movies -> {
            getRecyclerView(root, R.id.rvMAnimation, movies);
        });
        moviesViewModel.getMoviesComedyLiveData().observe(getViewLifecycleOwner(),movies -> {
            getRecyclerView(root,R.id.rvMComedy,movies);
        });
        moviesViewModel.getMoviesScienceFictionLiveData().observe(getViewLifecycleOwner(),movies -> {
            getRecyclerView(root,R.id.rvMScienceFiction, movies);
        });
        moviesViewModel.getMoviesFamilyLiveData().observe(getViewLifecycleOwner(),movies -> {
            getRecyclerView(root,R.id.rvMFamily,movies);
        });
        moviesViewModel.getMoviesHorrorLiveData().observe(getViewLifecycleOwner(),movies -> {
            getRecyclerView(root,R.id.rvMHorror,movies);
        });

        return root;
    }
    private void getRecyclerView(View root, int idRes, List<Movie> movies){
        RecyclerView rvMovies = root.findViewById(idRes);
        rvMovies.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        rvMovies.setAdapter(new CategoryMovieAdapter(movies));
    }

    private void GoToGenreFragment(String idGenre , View v){
        Bundle arg = new Bundle();
        arg.putString("id",idGenre);
        arg.putString("fragment","movie");
        Navigation.findNavController(v).navigate(R.id.action_navigation_movies_to_genreFragment,arg);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
