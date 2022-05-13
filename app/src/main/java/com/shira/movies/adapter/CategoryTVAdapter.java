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
import com.shira.movies.models.Tv;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryTVAdapter extends RecyclerView.Adapter<CategoryTVAdapter.ViewHolder> {
    private List<Tv> tvList;

    public CategoryTVAdapter(List<Tv> tvList) {
        this.tvList = tvList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(R.layout.item_category,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tv tvs = tvList.get(position);
        Picasso.get().load(tvs.getPosterLink())
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.ivPoster);
        holder.itemView.setRotationY(50f);
        holder.itemView.animate()
                .setDuration(2200)
                .rotationY(0f)
                .start();

        Bundle arg = new Bundle();
        arg.putString("idMovie",tvs.getId()+"");
        arg.putString("fragment","tv");
        holder.itemView.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_navigation_tv_to_detailsFragment,arg));

    }

    @Override
    public int getItemCount() {
        return tvList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivPoster;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPoster = itemView.findViewById(R.id.ivPoster);
        }
    }
}
