package com.uygaruyaniksoy.moviedb.searchActor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.uygaruyaniksoy.moviedb.BasicPresenter;
import com.uygaruyaniksoy.moviedb.R;
import com.uygaruyaniksoy.moviedb.actors.domain.Actor;
import com.uygaruyaniksoy.moviedb.searchActor.usecase.SearchActors;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class SearchActorActivity extends AppCompatActivity implements SearchActorsView, AbsListView.OnScrollListener {
    private SearchActorsPresenter presenter;
    private SearchActorsListAdapter adapter;
    private ListView actorsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_actor);

        actorsList = findViewById(R.id.actorsList);
        actorsList.setOnScrollListener(this);

        Intent intent = getIntent();
        String searchValue = intent.getStringExtra("searchValue");

        SearchActorsRepository actorsRepository = new SearchActorsRepository();
        presenter = new SearchActorsPresenter(this,
                actorsRepository,
                new SearchActors(actorsRepository),
                searchValue);
    }

    @Override
    public void initPresenter(BasicPresenter presenter) {
        presenter.start();
    }

    @Override
    public void displayActors(final List<Actor> actors) {
        if (adapter == null) {
            adapter = new SearchActorsListAdapter(getApplicationContext(), new ArrayList<Actor>());
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
    public void setSearchResultText(String searchValue) {
        TextView searchResult = findViewById(R.id.searchResultText);
        searchResult.setText(String.format("Actors with name: %s", searchValue));
    }

    @Override
    public void setSearchResultAmount(final int amount) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView searchAmount = findViewById(R.id.searchAmountFound);
                searchAmount.setText(String.format("Total: %d", amount));
            }
        });
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }

    @Override
    public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        int offset = 10;
        if (presenter != null && !presenter.isLoading() && firstVisibleItem + visibleItemCount + offset > totalItemCount) {
            presenter.searchActors();
            presenter.setLoading(true);
        }

    }

}
