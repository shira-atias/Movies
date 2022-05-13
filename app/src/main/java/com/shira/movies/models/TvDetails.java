package com.shira.movies.models;

import com.google.gson.annotations.SerializedName;

public class TvDetails {

    private int id;
    private String name;
    private String overview;
    @SerializedName("first_air_date")
    private String firstAirDate;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("backdrop_path")
    private String backdropPath;
    @SerializedName("homepage")
    private String homePage;
    @SerializedName("vote_average")
    private double voteAverage;

    public TvDetails() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }
    private static final String imagesBaseURL =  "https://image.tmdb.org/t/p/w500";
    public String getPosterLink(){
        return imagesBaseURL + posterPath;
    }
    public String getBackdropLink(){return imagesBaseURL + backdropPath;}

    @Override
    public String toString() {
        return "TvDetails{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", overview='" + overview + '\'' +
                ", firstAirDate='" + firstAirDate + '\'' +
                ", posterPath='" + posterPath + '\'' +
                ", backdropPath='" + backdropPath + '\'' +
                ", homePage='" + homePage + '\'' +
                ", voteAverage=" + voteAverage +
                '}';
    }
}
