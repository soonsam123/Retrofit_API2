package com.soon.karat.retrofit_api_2.Udacity.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soon.karat.retrofit_api_2.R;
import com.soon.karat.retrofit_api_2.Udacity.models.Degree;

import java.util.List;

public class DegreesAdapter extends RecyclerView.Adapter<DegreesAdapter.ItemHolder> {

    private List<Degree> degrees;
    private Context context;

    public DegreesAdapter(List<Degree> degrees, Context context) {
        this.degrees = degrees;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_degrees, parent, false);

        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {

        Degree singleDegree = degrees.get(position);

        holder.title.setText(singleDegree.getTitle());
        holder.summary.setText(singleDegree.getSummary());

    }

    @Override
    public int getItemCount() {
        return degrees.size();
    }


    public class ItemHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView summary;

        public ItemHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.txtView_title_itemDegrees);
            summary = itemView.findViewById(R.id.txtView_summary_itemDegrees);


        }

    }


}
