package com.uygaruyaniksoy.moviedb;

import com.uygaruyaniksoy.moviedb.actors.ActorsDataSource;
import com.uygaruyaniksoy.moviedb.actors.ActorsPresenter;
import com.uygaruyaniksoy.moviedb.actors.ActorsView;
import com.uygaruyaniksoy.moviedb.actors.usecase.GetActors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.any;

@RunWith(MockitoJUnitRunner.class)
public class ActorsPresenterTest {
    @Mock
    ActorsDataSource actorsRepository;

    @Mock
    ActorsView view;

    private ActorsPresenter presenter;

    @Before
    public void setUp() {
        presenter = new ActorsPresenter(view, actorsRepository, new GetActors(actorsRepository));
    }

    @Test
    public void shouldInitPresenterForViewWhenPresenterInitialized() {
        ActorsPresenter presenter = new ActorsPresenter(view, actorsRepository, new GetActors(actorsRepository));

        verify(view).initPresenter(presenter);
    }

    @Test
    public void shouldLoadActorsFromRepository() {
        presenter.loadActors();

        verify(actorsRepository).getActors(any(UseCase.Callback.class), any(GetActors.Request.class));
    }


}
