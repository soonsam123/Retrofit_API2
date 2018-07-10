package com.soon.karat.retrofit_api_2.Gerrit.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soon.karat.retrofit_api_2.R;
import com.soon.karat.retrofit_api_2.Gerrit.models.Change;

import java.util.List;

public class ChangeAdapter extends RecyclerView.Adapter<ChangeAdapter.ItemHolder> {

    private List<Change> changes;
    private Context context;

    public ChangeAdapter(List<Change> changes, Context context) {
        this.changes = changes;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_change, parent, false);

        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {

        Change singleChange = changes.get(position);

        holder.subject.setText(singleChange.getSubject());

    }

    @Override
    public int getItemCount() {
        return changes.size();
    }


    public class ItemHolder extends RecyclerView.ViewHolder {

        public TextView subject;

        public ItemHolder(View itemView) {
            super(itemView);

            subject = itemView.findViewById(R.id.text_subject);

        }

    }


}
