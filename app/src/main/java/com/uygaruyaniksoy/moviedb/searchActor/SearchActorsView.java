package com.uygaruyaniksoy.moviedb.searchActor;

import com.uygaruyaniksoy.moviedb.BasicView;
import com.uygaruyaniksoy.moviedb.actors.domain.Actor;

import java.util.List;

public interface SearchActorsView extends BasicView {
    void displayActors(List<Actor> actors);

    void setSearchResultText(String searchValue);

    void setSearchResultAmount(int amount);
}
