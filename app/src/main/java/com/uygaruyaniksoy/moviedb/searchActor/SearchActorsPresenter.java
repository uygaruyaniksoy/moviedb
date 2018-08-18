package com.uygaruyaniksoy.moviedb.searchActor;

import com.uygaruyaniksoy.moviedb.BasicPresenter;
import com.uygaruyaniksoy.moviedb.UseCase;
import com.uygaruyaniksoy.moviedb.UseCaseHandler;
import com.uygaruyaniksoy.moviedb.actors.domain.Actor;
import com.uygaruyaniksoy.moviedb.searchActor.usecase.SearchActors;

import java.util.ArrayList;
import java.util.List;

public class SearchActorsPresenter implements BasicPresenter {

    private int totalPage;
    private SearchActorsView view;
    private SearchActorsDataSource repository;
    private UseCase searchActors;
    private String searchValue;
    private boolean loading;

    private int page;

    public SearchActorsPresenter(SearchActorsView view, SearchActorsDataSource repository, SearchActors searchActors, String searchValue) {
        this.view = view;
        this.repository = repository;
        this.searchActors = searchActors;
        this.searchValue = searchValue;
        this.loading = false;
        this.page = 1;

        view.initPresenter(this);
    }

    @Override
    public void start() {
        searchActors();
        view.setSearchResultText(searchValue);
    }

    public void searchActors() {
        if (totalPage != 0 && page >= totalPage) return;
        UseCase.Request request = new SearchActors.Request(searchValue, page++);
        UseCaseHandler.getInstance().execute(searchActors, request, new UseCase.Callback<SearchActors.Response>() {
            @Override
            public void onSuccess(SearchActors.Response response) {
                List<Actor> actors = response.getActors();
                int amount = response.getAmount();
                view.displayActors(actors);
                view.setSearchResultAmount(amount);
                totalPage = response.getTotalPages();
                loading = false;
            }

            @Override
            public void onError() {
                view.displayActors(new ArrayList<Actor>());
                view.setSearchResultAmount(0);
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
}
