package com.uygaruyaniksoy.moviedb;

import java.util.List;

public class ActorsRepository implements ActorsDataSource {
    private final String LANGUAGE;
    private final String API_KEY;
    private int page;

    public ActorsRepository() {
        API_KEY = "2214c9dce5f82609d1b2d94318b87f9b";
        LANGUAGE = "en-US";
        page = 1;
    }

    public List<Actor> getActors() {
        return null;
    }

}
