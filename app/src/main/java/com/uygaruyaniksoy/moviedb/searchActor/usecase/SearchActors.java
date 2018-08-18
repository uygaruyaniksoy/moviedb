package com.uygaruyaniksoy.moviedb.searchActor.usecase;

import com.uygaruyaniksoy.moviedb.UseCase;
import com.uygaruyaniksoy.moviedb.actors.domain.Actor;
import com.uygaruyaniksoy.moviedb.searchActor.SearchActorsDataSource;
import com.uygaruyaniksoy.moviedb.searchActor.SearchActorsRepository;

import java.util.List;

public class SearchActors extends UseCase<SearchActors.Request, SearchActors.Response> {
    private SearchActorsRepository repository;

    public SearchActors(SearchActorsDataSource repository) {
        super();
        this.repository = (SearchActorsRepository) repository;
    }

    @Override
    public void run() {
        repository.searchActors(getCallback(), getRequest());
    }

    public static class Request implements UseCase.Request {
        private String searchValue;
        private int page;

        public int getPage() {
            return page;
        }

        public Request(String searchValue, int page) {
            this.searchValue = searchValue;
            this.page = page;
        }

        public String getSearchValue() {
            return searchValue;
        }
    }

    public static class Response implements UseCase.Response {
        private List<Actor> actors;
        private int amount;
        private int totalPages;

        public Response(List<Actor> actors, int amount, int totalPages) {
            this.actors = actors;
            this.amount = amount;
            this.totalPages = totalPages;
        }

        public List<Actor> getActors() {
            return actors;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public int getAmount() {
            return amount;

        }
    }
}

