/*
 * Copyright (C) LeafSoon 2018
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential.
 *
 * Written by Soon Sam <karatesoon@gmail.com>
 *
 */

package com.soon.karat.retrofit_api_2.StackOverflow.models;

import com.google.gson.annotations.SerializedName;

public class Answer {

    @SerializedName("answer_id")    // This is the name in the Json File;
    public int answerId;            // This is the name for the Java that I'll use throughout the application.

    @SerializedName("is_accepted")  // This is the name in the Json File;
    public boolean accepted;        // This is the name for the Java that I'll use throughout the application.

    public int score;

    @SerializedName("owner")
    public OwnerQuestion ownerQuestion;

    @Override
    public String toString() {
        return "AnswerId: " + answerId + " - Score: " + score + " - Accepted: " + (accepted ? "Yes" : "No");
    }
}
