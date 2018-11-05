/*
 * Copyright (C) LeafSoon 2018
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential.
 *
 * Written by Soon Sam <karatesoon@gmail.com>
 */

package com.soon.karat.retrofit_api_2.Github.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.soon.karat.retrofit_api_2.Github.Interface.GitHubService;
import com.soon.karat.retrofit_api_2.R;
import com.soon.karat.retrofit_api_2.BaseActivity;
import com.soon.karat.retrofit_api_2.Github.adapters.MyReposAdapter;
import com.soon.karat.retrofit_api_2.Github.models.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyReposActivity extends BaseActivity {

    private static final String TAG = "MyReposActivity";

    // Layout
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_repos);

        mRecyclerView = findViewById(R.id.recycler_view);
        mProgressBar = findViewById(R.id.progressbar);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GitHubService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubService service = retrofit.create(GitHubService.class);
        Call<List<Repo>> call = service.listRepos("soonsam123");

        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                if (!response.isSuccessful()) {
                    Log.i(TAG, "onResponse: " + response.code());
                } else {
                    List<Repo> repoList = response.body();
                    if (repoList != null) {
                        setupRecyclerView(repoList);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void setupRecyclerView(List<Repo> repoList) {

        mProgressBar.setVisibility(View.GONE); // Dismiss the progress bar before setting up the Recycler View

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mRecyclerView.setAdapter(new MyReposAdapter(repoList, this));
    }


}
