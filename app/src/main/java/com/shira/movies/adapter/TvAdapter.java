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
import com.shira.movies.database.actiontv.AddDeleteTvDelegate;
import com.shira.movies.database.actiontv.FavoriteDeleteTvDelegate;
import com.shira.movies.models.Tv;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TvAdapter extends RecyclerView.Adapter<TvAdapter.ViewHolder>{
    private List<Tv> tvList;
    private AddDeleteTvDelegate addDeleteTvDelegate;
    private FavoriteDeleteTvDelegate favoriteDeleteTvDelegate;

    // Default constructor
    public TvAdapter(List<Tv> tvList) {
        this.tvList = tvList;
    }
    // Add and Delete - tv
    public TvAdapter(List<Tv> tvList, AddDeleteTvDelegate addDeleteTvDelegate) {
        this.tvList = tvList;
        this.addDeleteTvDelegate = addDeleteTvDelegate;
    }
    // Delete constructor - Favorites
    public TvAdapter(List<Tv> tvList, FavoriteDeleteTvDelegate callbackTv) {
        this.tvList = tvList;
        this.favoriteDeleteTvDelegate = callbackTv;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(R.layout.item_genre,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tv tv = tvList.get(position);
        holder.tvTitle.setText(tv.getName());
        holder.tvDate.setText(tv.getFirstAirDate());

        if (tv.isFavorite()){
            holder.ivStar.setImageResource(R.drawable.star_gold);
        }

        holder.ivStar.setOnClickListener(v -> {
            if (!tv.isFavorite()){
                holder.ivStar.setImageResource(R.drawable.star_gold);
                holder.ivStar.setRotation(180f);
                holder.ivStar.animate()
                        .setDuration(1000)
                        .rotation(0f)
                        .start();
                tv.setFavorite(true);
                addDeleteTvDelegate.addTvToFavorites(tv);
            }else {
                holder.ivStar.setImageResource(R.drawable.ic_baseline_star_24);
                if(addDeleteTvDelegate == null){
                    favoriteDeleteTvDelegate.deleteFromFavorites(tv);
                    tvList.remove(position);
                    notifyItemRemoved(position);
                }else {
                    addDeleteTvDelegate.deleteTvFromFavorites(tv);
                }
                tv.setFavorite(false);
            }
        });

        Picasso.get().load(tv.getPosterLink())
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.ivPosterGenre);
        holder.ivPosterGenre.setRotationY(50f);
        holder.ivPosterGenre.animate()
                .setDuration(1500)
                .rotationY(0f)
                .start();

        Bundle arg = new Bundle();
        arg.putString("idMovie",tv.getId()+"");
        arg.putString("fragment","tv");
        holder.itemView.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_genreFragment_to_detailsFragment,arg);
        });

    }

    @Override
    public int getItemCount() {
        return tvList.size();
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
