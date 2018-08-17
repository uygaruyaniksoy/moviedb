package com.uygaruyaniksoy.moviedb;

import java.util.List;

class ActorsPresenter implements BasicPresenter {

    private ActorsView view;
    private ActorsDataSource repository;
    private UseCase getActors;

    public ActorsPresenter(ActorsView view, ActorsDataSource repository, GetActors getActors) {
        this.view = view;
        this.repository = repository;
        this.getActors = getActors;
        view.initPresenter(this);
    }

    @Override
    public void start() {

    }

    public void loadActors() {
        UseCase.Request request = new GetActors.Request(1);
        UseCaseHandler.getInstance().execute(getActors, request, new UseCase.Callback<GetActors.Response>() {
            @Override
            public void onSuccess(GetActors.Response response) {
                List<Actor> actors = response.getActors();
                view.displayActors(actors);
            }

            @Override
            public void onError() {

            }
        });

    }
}
