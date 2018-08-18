package com.uygaruyaniksoy.moviedb.actors.domain;

public class Actor {
    private String name;
    private Double popularity;
    private String profilePath;

    public Actor(String name, Double popularity, String profilePath) {

        this.name = name;
        this.popularity = popularity;
        this.profilePath = profilePath;
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
