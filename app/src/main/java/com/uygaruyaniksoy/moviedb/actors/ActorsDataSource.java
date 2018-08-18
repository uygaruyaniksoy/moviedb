package com.uygaruyaniksoy.moviedb.actors;

import com.uygaruyaniksoy.moviedb.actors.usecase.GetActors;
import com.uygaruyaniksoy.moviedb.UseCase;

public interface ActorsDataSource {
    void getActors(UseCase.Callback<GetActors.Response> callback, GetActors.Request request);
}
