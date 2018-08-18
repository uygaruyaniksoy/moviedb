package com.uygaruyaniksoy.moviedb.searchActor;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.uygaruyaniksoy.moviedb.AsyncImageTask;
import com.uygaruyaniksoy.moviedb.R;
import com.uygaruyaniksoy.moviedb.actors.domain.Actor;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchActorsListAdapter extends ArrayAdapter<Actor> {
    private final Context context;
    private List<Actor> actors;

    private Map<String, Bitmap> cache = new HashMap<>();

    public SearchActorsListAdapter(@NonNull Context context, @NonNull List<Actor> actors) {
        super(context, R.layout.row_item, actors);
        this.actors = actors;
        this.context = context;
    }

    public void pushActors(List<Actor> actors) {
        this.actors.addAll(this.actors.size(), actors);
        this.actors.sort(new Comparator<Actor>() {
            @Override
            public int compare(Actor actor1, Actor actor2) {
                return -Double.compare(actor1.getPopularity(), actor2.getPopularity());
            }
        });
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            convertView = vi.inflate(R.layout.row_item, null);
        }
        ImageView image = convertView.findViewById(R.id.actorImage);
        image.setVisibility(View.INVISIBLE);

        Actor actor = getItem(position);

        TextView name = convertView.findViewById(R.id.actorName);
        name.setText(actor.getName());
        TextView popularityScore = convertView.findViewById(R.id.actorPopularityScore);
        popularityScore.setText(actor.getPopularity().toString());
        if (cache.containsKey(actor.getProfilePath())) {
            image.setImageBitmap(cache.get(actor.getProfilePath()));
            image.setVisibility(View.VISIBLE);
        } else {
            new AsyncImageTask(image, cache).execute(actor.getProfilePath());
        }
        return convertView;
    }
}

