package com.uygaruyaniksoy.moviedb.actors;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.uygaruyaniksoy.moviedb.actors.domain.Actor;
import com.uygaruyaniksoy.moviedb.actors.usecase.GetActors;
import com.uygaruyaniksoy.moviedb.BasicPresenter;
import com.uygaruyaniksoy.moviedb.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ActorsView {
    private BasicPresenter presenter;
    private ActorsListAdapter adapter;
    private ListView actorsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actorsList = findViewById(R.id.actorsList);

        ActorsRepository actorsRepository = new ActorsRepository();
        presenter = new ActorsPresenter(this,
                actorsRepository,
                new GetActors(actorsRepository));
    }

    @Override
    public void initPresenter(BasicPresenter presenter) {
        this.presenter = presenter;
        presenter.start();
    }

    @Override
    public void displayActors(List<Actor> actors) {
        if (adapter == null) {
            adapter = new ActorsListAdapter(getApplicationContext(), new ArrayList<Actor>());
        }
        adapter.pushActors(actors);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                actorsList.setAdapter(adapter);
            }
        });
    }
}
