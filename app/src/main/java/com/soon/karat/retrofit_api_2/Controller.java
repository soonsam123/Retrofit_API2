package com.soon.karat.retrofit_api_2;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.soon.karat.retrofit_api_2.Gerrit.Interface.GerritAPI;
import com.soon.karat.retrofit_api_2.Gerrit.models.Change;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller implements Callback<List<Change>>{

    private static final String TAG = "Controller";

    private static final String BASE_URL = "https://git.eclipse.org/r/";

    private List<Change> changeList = new ArrayList<>();

    public void start() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        GerritAPI gerritAPI = retrofit.create(GerritAPI.class);

        Call<List<Change>> call = gerritAPI.loadChanges("status:open");
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Change>> call, Response<List<Change>> response) {
        if (!response.isSuccessful()) {
            Log.i(TAG, "onResponse: " + response.code());
        } else {
            List<Change> changeList = response.body();
            Log.i(TAG, "onResponse: SUCCESS");
        }
    }

    @Override
    public void onFailure(Call<List<Change>> call, Throwable t) {
        Log.i(TAG, "onFailure: " + t.getMessage());
    }
}
