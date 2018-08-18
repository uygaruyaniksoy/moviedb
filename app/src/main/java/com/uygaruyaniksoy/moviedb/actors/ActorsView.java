package com.uygaruyaniksoy.moviedb.actors;

import com.uygaruyaniksoy.moviedb.actors.domain.Actor;
import com.uygaruyaniksoy.moviedb.BasicView;

import java.util.List;

public interface ActorsView extends BasicView {
    void displayActors(List<Actor> actors);
}
