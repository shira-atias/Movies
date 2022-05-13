package com.shira.movies.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.shira.movies.R;
import com.shira.movies.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryMovieAdapter extends RecyclerView.Adapter<CategoryMovieAdapter.ViewHolder>{
    private List<Movie> movies;

    public CategoryMovieAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(R.layout.item_category,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        Picasso.get().load(movie.getPosterLink())
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.ivPoster);
    holder.itemView.setRotationY(50f);
        holder.itemView.animate()
                .setDuration(2200)
                .rotationY(0f)
                .start();
        Bundle arg = new Bundle();
        arg.putString("idMovie",movie.getId()+"");
        arg.putString("fragment","movie");
        holder.itemView.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_navigation_movies_to_detailsFragment,arg));

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivPoster;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPoster = itemView.findViewById(R.id.ivPoster);
        }
    }
}
