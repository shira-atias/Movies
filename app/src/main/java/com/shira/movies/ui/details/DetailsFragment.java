package com.shira.movies.ui.details;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.shira.movies.R;
import com.shira.movies.databinding.DetailsFragmentBinding;

import com.shira.movies.models.Movie;
import com.squareup.picasso.Picasso;

import retrofit2.http.Url;

public class DetailsFragment extends Fragment {

    private DetailsViewModel movieDetailViewModel;

    private DetailsFragmentBinding binding;


    public static DetailsFragment newInstance() {
        return new DetailsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DetailsFragmentBinding.inflate(inflater,container,false);
        View root = binding.getRoot();

        movieDetailViewModel = new ViewModelProvider(this).get(DetailsViewModel.class);

        Bundle bag = getArguments();
        String idM = bag.getString("idMovie");
        String fragment = bag.getString("fragment");


        if (fragment.equals("movie")){

            movieDetailViewModel.getMovieDetails(idM);
            movieDetailViewModel.getMoviesLiveData().observe(getViewLifecycleOwner(),movieDetails -> {
                Picasso.get().load(movieDetails.getBackdropLink()).into(binding.ivPosterBD);
                Picasso.get().load(movieDetails.getPosterLink()).into(binding.ivPosterD);
                binding.tvNameD.setText(movieDetails.getTitle());
                if (movieDetails.getRunTime() == 0){
                    binding.tvRunTime.setText("");
                }else {
                    binding.tvRunTime.setText(movieDetails.getRunTime()+" minutes");
                }

                binding.tvAverage.setText(movieDetails.getVoteAverage()+"/10");
                if (movieDetails.getOverview() == ""){
                    binding.tvOverview.setText("Ther is on information");
                }else {
                    binding.tvOverview.setText(movieDetails.getOverview());
                }
                binding.tvWeb.setOnClickListener(v -> {
                    if (movieDetails.getHomePage().contains("https")){
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(movieDetails.getHomePage()));
                        startActivity(intent);
                    }else {
                        Toast.makeText(getContext(), "The website does not exist", Toast.LENGTH_SHORT).show();
                    }

                });
            });
        }else if (fragment.equals("tv")){
            movieDetailViewModel.getTvLiveData(idM).observe(getViewLifecycleOwner(),tv -> {
                Picasso.get().load(tv.getPosterLink()).into(binding.ivPosterD);
                Picasso.get().load(tv.getBackdropLink()).into(binding.ivPosterBD);
                binding.tvNameD.setText(tv.getName());
                binding.tvAverage.setText(tv.getVoteAverage()+"/10");
                if (tv.getOverview().equals("")){
                    binding.tvOverview.setText("Sorry there is on information");
                }else {
                    binding.tvOverview.setText(tv.getOverview());
                }
                binding.tvWeb.setOnClickListener(v -> {
                    if (tv.getHomePage().contains("https")){
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(tv.getHomePage()));
                        startActivity(intent);
                    }else {
                        Toast.makeText(getContext(), "The website does not exist", Toast.LENGTH_SHORT).show();
                    }

                });
            });
        }

        return root;
    }


}