package com.soon.karat.retrofit_api_2.StackOverflow.models;

import com.google.gson.annotations.SerializedName;

public class Question {

    public String title;
    public String body;

    @SerializedName("question_id")      // This is the name in the Json Object;
    public String questionId;           // This is the name I'll through my application.

    @Override
    public String toString() {
        return "Question - " + title;
    }
}
