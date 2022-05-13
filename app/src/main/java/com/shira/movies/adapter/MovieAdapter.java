package com.shira.movies.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.shira.movies.R;
import com.shira.movies.database.actionmovie.AddDeleteMovieDelegate;
import com.shira.movies.database.actionmovie.FavoritesDeleteMovieDelegate;
import com.shira.movies.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{

    private List<Movie> movies;
    private AddDeleteMovieDelegate addDeleteDelegate; //dbdelete
    private FavoritesDeleteMovieDelegate genreAdapterFavorites;

    // Add and Delete - movies
    public MovieAdapter(List<Movie> genreMovies, AddDeleteMovieDelegate delegate) {
        this.movies = genreMovies;
        this.addDeleteDelegate = delegate;
    }

    // Delete constructor - Favorites
    public MovieAdapter(List<Movie> genreMovies, FavoritesDeleteMovieDelegate callbackMovie) {
        this.movies = genreMovies;
        this.genreAdapterFavorites = callbackMovie;
    }

    // Default constructor
    public MovieAdapter(List<Movie> genreMovies) {
        this.movies = genreMovies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(R.layout.item_genre,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Movie movie = movies.get(position);
        holder.tvTitle.setText(movie.getTitle());
        holder.tvDate.setText(movie.getReleaseDate());

        if(movie.getIsFavorite()) {
            holder.ivStar.setImageResource(R.drawable.star_gold);
        }

        holder.ivStar.setOnClickListener(v -> {
            if (!movie.getIsFavorite()){
                holder.ivStar.setImageResource(R.drawable.star_gold);
                holder.ivStar.setRotation(180f);
                holder.ivStar.animate()
                        .setDuration(1000)
                        .rotation(0f)
                        .start();
                movie.setIsFavorite(true);
                addDeleteDelegate.addMovieToFavorites(movie);

            }else {
                holder.ivStar.setImageResource(R.drawable.ic_baseline_star_24);
                if(addDeleteDelegate == null) {
                    genreAdapterFavorites.deleteFromFavoritesF(movie);
                    movies.remove(position);
                    notifyItemRemoved(position);
                }else {
                    addDeleteDelegate.deleteFromFavorites(movie);
                }
                movie.setIsFavorite(false);
            }


        });
        Picasso.get().load(movie.getPosterLink())
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.ivPosterGenre);
        holder.ivPosterGenre.setRotationY(50f);
        holder.ivPosterGenre.animate()
                .setDuration(1500)
                .rotationY(0f)
                .start();

        Bundle arg = new Bundle();
        arg.putString("idMovie",movie.getId()+"");
        arg.putString("fragment","movie");
        holder.itemView.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_genreFragment_to_detailsFragment,arg);
        });

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        TextView tvDate;
        ImageView ivPosterGenre;
        ImageView ivStar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDate = itemView.findViewById(R.id.tvDate);
            ivPosterGenre = itemView.findViewById(R.id.ivPosterGenre);
            ivStar = itemView.findViewById(R.id.ivStar);
        }
    }
}
