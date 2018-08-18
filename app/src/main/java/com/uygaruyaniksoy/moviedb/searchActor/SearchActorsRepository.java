package com.uygaruyaniksoy.moviedb.searchActor;

import android.net.Uri;

import com.uygaruyaniksoy.moviedb.UseCase;
import com.uygaruyaniksoy.moviedb.UseCaseScheduler;
import com.uygaruyaniksoy.moviedb.actors.domain.Actor;
import com.uygaruyaniksoy.moviedb.searchActor.usecase.SearchActors;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SearchActorsRepository implements SearchActorsDataSource {
    private final String LANGUAGE;
    private final String API_KEY;

    public SearchActorsRepository() {
        API_KEY = "2214c9dce5f82609d1b2d94318b87f9b";
        LANGUAGE = "en-US";
    }

    public void searchActors(final UseCase.Callback<SearchActors.Response> callback, final SearchActors.Request request) {
        UseCaseScheduler.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Uri.Builder uriBuilder = new Uri.Builder();
                    uriBuilder.scheme("https")
                            .authority("api.themoviedb.org")
                            .appendPath("3")
                            .appendPath("search")
                            .appendPath("person")
                            .appendQueryParameter("api_key", API_KEY)
                            .appendQueryParameter("language", LANGUAGE)
                            .appendQueryParameter("query", request.getSearchValue())
                            .appendQueryParameter("page", String.valueOf(request.getPage()));

                    URL url = new URL(uriBuilder.toString());
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                    InputStream stream = new BufferedInputStream(urlConnection.getInputStream());
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
                    StringBuilder stringBuilder = new StringBuilder();

                    String inputString;
                    while ((inputString = bufferedReader.readLine()) != null) {
                        stringBuilder.append(inputString);
                    }

                    JSONObject result = new JSONObject(stringBuilder.toString());

                    JSONArray actors = result.getJSONArray("results");
                    List<Actor> actorList = new ArrayList<>();
                    for (int i = 0; i < actors.length(); i++) {
                        JSONObject actor = actors.getJSONObject(i);
                        actorList.add(new Actor(actor.getString("name"), Double.valueOf(actor.getString("popularity")), actor.getString("profile_path")));
                    }

                    urlConnection.disconnect();

                    // create actors array
                    SearchActors.Response response = new SearchActors.Response(actorList, result.getInt("total_results"), result.getInt("total_pages"));
                    callback.onSuccess(response);
                } catch (IOException | JSONException e) {
                    callback.onError();
//                     e.printStackTrace();
                }
            }
        });
    }

}
