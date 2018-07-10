package com.soon.karat.retrofit_api_2.Udacity.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soon.karat.retrofit_api_2.R;
import com.soon.karat.retrofit_api_2.Udacity.models.Track;

import java.util.List;

public class TracksAdapter extends RecyclerView.Adapter<TracksAdapter.ItemHolder> {

    private List<Track> tracks;
    private Context context;

    public TracksAdapter(List<Track> tracks, Context context) {
        this.tracks = tracks;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tracks, parent, false);

        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {

        Track singleTrack = tracks.get(position);

        holder.name.setText(singleTrack.getName());
        holder.description.setText(singleTrack.getDescription());

    }

    @Override
    public int getItemCount() {
        return tracks.size();
    }


    public class ItemHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView description;

        public ItemHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.txtView_name_itemTracks);
             description = itemView.findViewById(R.id.txtView_description_itemTracks);


        }

    }


}
