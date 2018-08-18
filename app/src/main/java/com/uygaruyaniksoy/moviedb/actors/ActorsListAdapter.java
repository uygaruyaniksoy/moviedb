package com.uygaruyaniksoy.moviedb.actors;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActorsListAdapter extends ArrayAdapter<Actor> {
    private final Context context;
    private List<Actor> actors;

    private Map<String, Bitmap> cache = new HashMap<>();

    public ActorsListAdapter(@NonNull Context context, @NonNull List<Actor> actors) {
        super(context, R.layout.row_item, actors);
        this.actors = actors;
        this.context = context;
    }

    public void pushActors(List<Actor> actors) {
        this.actors.addAll(actors);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            convertView = vi.inflate(R.layout.row_item, null);
        }

        Actor actor = getItem(position);

        TextView name = convertView.findViewById(R.id.actorName);
        name.setText(actor.getName());
        TextView popularityScore = convertView.findViewById(R.id.actorPopularityScore);
        popularityScore.setText(actor.getPopularity().toString());
        ImageView image = convertView.findViewById(R.id.actorImage);
        image.setVisibility(View.INVISIBLE);
        if (cache.containsKey(actor.getProfilePath())) {
            image.setImageBitmap(cache.get(actor.getProfilePath()));
            image.setVisibility(View.VISIBLE);
        } else {
            new AsyncImageTask(image, cache).execute(actor.getProfilePath());
        }
        return convertView;
    }
}
