package com.soon.karat.retrofit_api_2.Gerrit.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.soon.karat.retrofit_api_2.Gerrit.Interface.GerritAPI;
import com.soon.karat.retrofit_api_2.R;
import com.soon.karat.retrofit_api_2.BaseActivity;
import com.soon.karat.retrofit_api_2.Gerrit.adapters.ChangeAdapter;
import com.soon.karat.retrofit_api_2.Gerrit.models.Change;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChangeActivity extends BaseActivity {

    private static final String TAG = "ChangeActivity";

    // Layout
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);

        setupWidgets();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GerritAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        GerritAPI gerritAPI = retrofit.create(GerritAPI.class);
        Call<List<Change>> call = gerritAPI.loadChangesById("jdt%2Feclipse.jdt.core~master~I21f80eb75aad90094d46f4a7f0255b12382791a7");

        call.enqueue(new Callback<List<Change>>() {
            @Override
            public void onResponse(Call<List<Change>> call, Response<List<Change>> response) {
                if (!response.isSuccessful()) {
                    Log.i(TAG, "onResponse: " + response.code());
                } else {
                    List<Change> changeList = response.body();
                    if (changeList != null) {
                        setupRecyclerView(changeList);
                    } else {
                        Log.i(TAG, "onResponse: The Change List is empty");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Change>> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t.getMessage());
            }
        });

    }


    private void setupWidgets() {
        mRecyclerView = findViewById(R.id.recyclerView_activityChange);
        mProgressBar = findViewById(R.id.progressBar_activityChange);
    }

    public void setupRecyclerView(List<Change> changeList) {
        mProgressBar.setVisibility(View.GONE);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mRecyclerView.setAdapter(new ChangeAdapter(changeList, this));
    }

}
