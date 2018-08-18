package com.uygaruyaniksoy.moviedb.actors;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.uygaruyaniksoy.moviedb.actors.domain.Actor;
import com.uygaruyaniksoy.moviedb.actors.usecase.GetActors;
import com.uygaruyaniksoy.moviedb.BasicPresenter;
import com.uygaruyaniksoy.moviedb.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ActorsView, AbsListView.OnScrollListener {
    private BasicPresenter presenter;
    private ActorsListAdapter adapter;
    private ListView actorsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actorsList = findViewById(R.id.actorsList);
        actorsList.setOnScrollListener(this);

        ActorsRepository actorsRepository = new ActorsRepository();
        presenter = new ActorsPresenter(this,
                actorsRepository,
                new GetActors(actorsRepository));
    }

    @Override
    public void initPresenter(BasicPresenter presenter) {
        presenter.start();
    }

    @Override
    public void displayActors(final List<Actor> actors) {
        if (adapter == null) {
            adapter = new ActorsListAdapter(getApplicationContext(), new ArrayList<Actor>());
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    actorsList.setAdapter(adapter);
                }
            });
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.pushActors(actors);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }

    @Override
    public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        int offset = 10;
        if (presenter != null && !((ActorsPresenter) presenter).isLoading() && firstVisibleItem + visibleItemCount + offset > totalItemCount) {
            ((ActorsPresenter) presenter).loadActors();
            ((ActorsPresenter) presenter).setLoading(true);
        }

    }
}
