package com.uygaruyaniksoy.moviedb;

import java.util.List;

class ActorsPresenter implements BasicPresenter {

    private ActorsView view;
    private ActorsDataSource repository;

    public ActorsPresenter(ActorsView view, ActorsDataSource repository) {
        this.view = view;
        this.repository = repository;
        view.initPresenter(this);
    }

    @Override
    public void start() {

    }

    public void loadActors() {
        List<Actor> actors = repository.getActors();

        view.displayActors(actors);
    }
}
