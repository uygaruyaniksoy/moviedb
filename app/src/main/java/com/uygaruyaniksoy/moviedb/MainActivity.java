package com.uygaruyaniksoy.moviedb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ActorsView {
    BasicPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActorsRepository actorsRepository = new ActorsRepository();
        presenter = new ActorsPresenter(this,
                actorsRepository,
                new GetActors());
    }

    @Override
    public void initPresenter(BasicPresenter presenter) {

        this.presenter = presenter;
        presenter.start();
    }

    @Override
    public void displayActors(List<Actor> actors) {

    }
}
