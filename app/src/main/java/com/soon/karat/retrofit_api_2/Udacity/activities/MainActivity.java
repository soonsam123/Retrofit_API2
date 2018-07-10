package com.soon.karat.retrofit_api_2.Udacity.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.soon.karat.retrofit_api_2.Udacity.Interface.UdacityService;
import com.soon.karat.retrofit_api_2.R;
import com.soon.karat.retrofit_api_2.BaseActivity;
import com.soon.karat.retrofit_api_2.Udacity.adapters.CoursesAdapter;
import com.soon.karat.retrofit_api_2.Udacity.models.Course;
import com.soon.karat.retrofit_api_2.Udacity.models.UdacityCatalog;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    // Layout
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupWidgets();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UdacityService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UdacityService service = retrofit.create(UdacityService.class);
        Call<UdacityCatalog> requestCatalog = service.listCatalog();

        // 1. requestCatalog.enqueue(): Execute asynchronous, while the ui is being built;
        // 2. requestCatalog.execute(): Execute synchronous, after the ui is finished.
        requestCatalog.enqueue(new Callback<UdacityCatalog>() {
            @Override
            public void onResponse(Call<UdacityCatalog> call, Response<UdacityCatalog> response) {

                if (response.isSuccessful()) {
                    UdacityCatalog catalog = response.body();
                    if (catalog.getCourses() != null) { setupRecyclerView(catalog.getCourses()); }
                } else {
                    Log.i(TAG, "onResponse: " + response.code());
                }

            }

            @Override
            public void onFailure(Call<UdacityCatalog> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t.getMessage());
            }
        });

    }


    private void setupWidgets() {
        Log.i(TAG, "setupWidgets: Settings up the widgets");

        mRecyclerView = findViewById(R.id.recyclerView_activityMain);
    }

    private void setupRecyclerView(List<Course> courseList) {
        Log.i(TAG, "setupRecyclerView: Settings up the recycler view");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mRecyclerView.setAdapter(new CoursesAdapter(courseList, this));

    }

}
