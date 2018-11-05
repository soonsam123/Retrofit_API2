/*
 * Copyright (C) LeafSoon 2018
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential.
 *
 * Written by Soon Sam <karatesoon@gmail.com>
 *
 */

package com.soon.karat.retrofit_api_2.StackOverflow.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.soon.karat.retrofit_api_2.R;
import com.soon.karat.retrofit_api_2.StackOverflow.models.Answer;
import com.soon.karat.retrofit_api_2.utils.GlideApp;

import java.util.List;

public class StackAdapter extends RecyclerView.Adapter<StackAdapter.ItemHolder> {

    // The recycler view will display a list of answers
    private List<Answer> answers;

    public StackAdapter(List<Answer> answers) {
        this.answers = answers;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // He took a layout from android to be the item for the recycler view
        // This layout has only a TextView with id = text1
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_answers, parent, false);

        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {

        // Gets the answer by given the position;
        Answer answer = answers.get(position);

        // Set the text in the item to the answer as a String
        holder.answersInfo.setText(answer.toString());

        GlideApp.with(holder.profilePicture.getContext())
                .load(answer.ownerQuestion.profile_image)
                .centerCrop()
                .into(holder.profilePicture);

        // Save each questions Id in a Tag, then I can access this latter with
        // holder.itemView.getTag(answerId)
        holder.itemView.setTag(answer.answerId);

    }

    @Override
    public int getItemCount() {
        return answers.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {

        // Instantiate the TextView (id=text1) in the android.R.layout.simple_selectable_list_item
        public TextView answersInfo;
        public ImageView profilePicture;

        public ItemHolder(View itemView) {
            super(itemView);
            answersInfo = itemView.findViewById(R.id.text_answer_info);
            profilePicture = itemView.findViewById(R.id.image_profile);
        }
    }

}

