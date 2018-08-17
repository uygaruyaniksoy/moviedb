package com.uygaruyaniksoy.moviedb;

class UseCaseHandler {
    private static UseCaseHandler instance;

    public static UseCaseHandler getInstance() {
        if (instance == null) instance = new UseCaseHandler();
        return instance;
    }

    public <R extends UseCase.Response> void execute(UseCase useCase,
                                                     UseCase.Request request,
                                                     UseCase.Callback<R> callback) {
        useCase.setRequest(request);
        useCase.setCallback(callback);
        useCase.run();
    }
}
