package com.uygaruyaniksoy.moviedb.actors.domain;

public class Actor {
    private String name;
    private Double popularity;
    private String profilePath;

    public static String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500";

    public Actor(String name, Double popularity, String profilePath) {

        this.name = name;
        this.popularity = popularity;
        this.profilePath = IMAGE_BASE_URL + profilePath;
    }

    public String getName() {
        return name;
    }

    public Double getPopularity() {
        return popularity;
    }

    public String getProfilePath() {
        return profilePath;
    }
}
