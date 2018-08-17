package com.uygaruyaniksoy.moviedb;

import java.util.List;

public class GetActors extends UseCase<GetActors.Request, GetActors.Response> {
    private ActorsDataSource repository;

    public GetActors(ActorsDataSource repository) {
        this.repository = repository;
    }

    @Override
    public void run() {
        repository.getActors(getCallback(), getRequest());
        // make getActors receive a callback and fetch actors from API
    }

    public static class Request implements UseCase.Request {
        private int page;

        public Request(int page) {
            this.page = page;
        }

        public int getPage() {
            return page;
        }
    }

    public static class Response implements UseCase.Response {
        private List<Actor> actors;

        public Response(List<Actor> actors) {
            this.actors = actors;
        }

        public List<Actor> getActors() {
            return actors;
        }
    }
}
