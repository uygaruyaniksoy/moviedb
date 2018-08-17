package com.uygaruyaniksoy.moviedb;

import java.util.List;

public interface ActorsDataSource {
    public void getActors(UseCase.Callback<GetActors.Response> callback, GetActors.Request request);
}
