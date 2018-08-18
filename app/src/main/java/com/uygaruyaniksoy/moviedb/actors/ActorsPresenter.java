package com.uygaruyaniksoy.moviedb.actors;

import com.uygaruyaniksoy.moviedb.actors.domain.Actor;
import com.uygaruyaniksoy.moviedb.actors.usecase.GetActors;
import com.uygaruyaniksoy.moviedb.BasicPresenter;
import com.uygaruyaniksoy.moviedb.UseCase;
import com.uygaruyaniksoy.moviedb.UseCaseHandler;

import java.util.ArrayList;
import java.util.List;

public class ActorsPresenter implements BasicPresenter {

    private ActorsView view;
    private ActorsDataSource repository;
    private UseCase getActors;
    private boolean loading;

    private int actorsPage;
    public ActorsPresenter(ActorsView view, ActorsDataSource repository, GetActors getActors) {
        this.view = view;
        this.repository = repository;
        this.getActors = getActors;
        this.loading = false;

        actorsPage = 1;
        view.initPresenter(this);
    }

    @Override
    public void start() {
        loadActors();
    }

    public void loadActors() {
        UseCase.Request request = new GetActors.Request(actorsPage++);
        UseCaseHandler.getInstance().execute(getActors, request, new UseCase.Callback<GetActors.Response>() {
            @Override
            public void onSuccess(GetActors.Response response) {
                List<Actor> actors = response.getActors();
                view.displayActors(actors);
                loading = false;
            }

            @Override
            public void onError() {
                view.displayActors(new ArrayList<Actor>());
                loading = false;
            }
        });
    }

    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    public void navigateToSearchPage(String actorname) {
        view.navigateToSearchActorsActivity(actorname);
    }
}
