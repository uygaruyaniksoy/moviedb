package com.uygaruyaniksoy.moviedb.searchActor;

import com.uygaruyaniksoy.moviedb.UseCase;
import com.uygaruyaniksoy.moviedb.searchActor.usecase.SearchActors;

public interface SearchActorsDataSource {
    void searchActors(final UseCase.Callback<SearchActors.Response> callback, final SearchActors.Request request) ;
}
