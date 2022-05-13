package com.shira.movies.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MoviesResponse {
    @SerializedName("results")
    private ArrayList<Movie> movies;

    public MoviesResponse(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    @Override
    public String toString() {
        return "MoviesResponse{" +
                "movies=" + movies +
                '}';
    }
}
