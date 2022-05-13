package com.shira.movies.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TvResponse {
    @SerializedName("results")
    private ArrayList<Tv> tvs;

    public TvResponse(ArrayList<Tv> tvs) {
        this.tvs = tvs;
    }

    public ArrayList<Tv> getTvs() {
        return tvs;
    }

    @Override
    public String toString() {
        return "TvResponse{" +
                "tvs=" + tvs +
                '}';
    }
}
