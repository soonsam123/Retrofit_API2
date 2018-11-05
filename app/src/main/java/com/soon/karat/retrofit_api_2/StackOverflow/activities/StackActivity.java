/*
 * Copyright (C) LeafSoon 2018
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential.
 *
 * Written by Soon Sam <karatesoon@gmail.com>
 *
 */

package com.soon.karat.retrofit_api_2.StackOverflow.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.soon.karat.retrofit_api_2.StackOverflow.Interface.StackOverflowAPI;
import com.soon.karat.retrofit_api_2.R;
import com.soon.karat.retrofit_api_2.StackOverflow.adapters.StackAdapter;
import com.soon.karat.retrofit_api_2.BaseActivity;
import com.soon.karat.retrofit_api_2.StackOverflow.models.Answer;
import com.soon.karat.retrofit_api_2.StackOverflow.models.Question;
import com.soon.karat.retrofit_api_2.utils.ListWrapper;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StackActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "StackActivity";

    // Layout
    private Spinner mSpinnerQuestions;
    private TextView mBodyQuestion;
    private RecyclerView mRecyclerView;
    private FloatingActionButton mAuthenticateButton;
    private ProgressBar mProgressBar;

    // Vars
    private String token;

    // Api
    private StackOverflowAPI stackOverflowAPI;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stack);

        // 1. Set up the widgets;
        mSpinnerQuestions = findViewById(R.id.spinner);
        mProgressBar = findViewById(R.id.progressbar);
        mBodyQuestion = findViewById(R.id.text_question_body);

        // 2. The user selected one of the items in the spinner;
        mSpinnerQuestions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // 2.1. Get the Question Object from the selected answer;
                Question question = (Question) parent.getAdapter().getItem(position);

                // 2.2. Display the body of the question before the answers;
                mBodyQuestion.setText(question.body); // The data of the questions is already loaded whe I set up the spinner

                // 2.3. Dismiss the Recycler View as I'll load new answers;
                mRecyclerView.setVisibility(View.GONE);

                // 2.4. ProgressBar to load the data;
                mProgressBar.setVisibility(View.VISIBLE);

                // 2.5. Gets the answers from the specific question by giving the questionId.
                stackOverflowAPI.getAnswersForQuestion(question.questionId).enqueue(answersCallback);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mAuthenticateButton = findViewById(R.id.button_authenticate);
        mAuthenticateButton.setOnClickListener(this);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(StackActivity.this));

        // 3. Creates the API so I'll be able to enqueue the Interface service.
        createStackoverflowAPI();

        // 4. Use the service (StackOverflowAPI) to get all the Questions and set up the spinner.
        // Note: We set up the spinner in the Callback
        stackOverflowAPI.getQuestions().enqueue(questionsCallback);


    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: Running onResume");
        if (token != null) {
            mAuthenticateButton.setEnabled(false);
        }
    }

    private void createStackoverflowAPI() {
        // 1. Creates the Gson Object;
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        // 2. Creates the Retrofit by parsin the BASE_URL and the Converter;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(StackOverflowAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        // 3. Creates the service (StackOverflowAPI) by using the retrofit.
        stackOverflowAPI = retrofit.create(StackOverflowAPI.class);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case android.R.id.text1:
                if (token != null) {
                    // TODO
                } else {
                    Toast.makeText(this, "You need to authenticate first", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.button_authenticate:
                // TODO
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i(TAG, "onActivityResult: Running onActivityResult");
        if (resultCode == RESULT_OK && requestCode == 1) {
            token = data.getStringExtra("token");
        }
    }


    /* =============== Callback for the Questions =============== */
    Callback<ListWrapper<Question>> questionsCallback = new Callback<ListWrapper<Question>>() {
        @Override
        public void onResponse(Call<ListWrapper<Question>> call, Response<ListWrapper<Question>> response) {
            if (response.isSuccessful()) {

                // 1. Creates a ListWrapper of questions;
                ListWrapper<Question> questions = response.body();

                // 2. Creates the adapter for the spinner;
                // Note: Notice that to call the List<Question> I need to call questions.items
                // that's the same of calling "response.body().items"
                ArrayAdapter<Question> arrayAdapter = new ArrayAdapter<>(StackActivity.this
                        , android.R.layout.simple_spinner_dropdown_item
                        , questions.items);

                // 3. Set the adapter for the spinner.
                mSpinnerQuestions.setAdapter(arrayAdapter);

            } else {
                Log.i(TAG, "onResponse: Code: " + response.code() + " - Message: " + response.message());
            }
        }

        @Override
        public void onFailure(Call<ListWrapper<Question>> call, Throwable t) {
            t.printStackTrace();
        }
    };


    /* =============== Callback for the Answers =============== */
    Callback<ListWrapper<Answer>> answersCallback = new Callback<ListWrapper<Answer>>() {
        @Override
        public void onResponse(Call<ListWrapper<Answer>> call, Response<ListWrapper<Answer>> response) {
            if (response.isSuccessful()) {

                // 1. Get the list of answers by calling the ListWrapper.items;
                // Note: response.body() returns a ListWrapper<List<Answers>> wrapper
                // to access the List<Answer> I need to call wrapper.items
                // that's why I calling "response.body().items"
                List<Answer> answers = new ArrayList<>(response.body().items);

                // 2. Data is loaded, dismiss the progressBar;
                mProgressBar.setVisibility(View.GONE);

                // 3. Show the Recycler View before setting the adapter;
                mRecyclerView.setVisibility(View.VISIBLE);

                // 4. Set the adapter with list we've got so far.
                mRecyclerView.setAdapter(new StackAdapter(answers));

            } else {
                Log.i(TAG, "onResponse: Code: " + response.code() + " - Message: " + response.message());
            }
        }

        @Override
        public void onFailure(Call<ListWrapper<Answer>> call, Throwable t) {
            t.printStackTrace();
        }
    };


    /* =============== Callback for Upvoted =============== */
    Callback<ResponseBody> upvoteCallback = new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            if (response.isSuccessful()) {
                Toast.makeText(StackActivity.this, "Upvoted Sucessful", Toast.LENGTH_SHORT).show();
            } else {
                Log.i(TAG, "onResponse: Code: " + response.code() + " - Message: " + response.message());
                Toast.makeText(StackActivity.this, "You've already upvoted this answer", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            t.printStackTrace();
        }
    };

}
