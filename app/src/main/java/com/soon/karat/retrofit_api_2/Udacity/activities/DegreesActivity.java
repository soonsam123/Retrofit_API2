package com.soon.karat.retrofit_api_2.Udacity.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.soon.karat.retrofit_api_2.Udacity.Interface.UdacityService;
import com.soon.karat.retrofit_api_2.R;
import com.soon.karat.retrofit_api_2.BaseActivity;
import com.soon.karat.retrofit_api_2.Udacity.adapters.DegreesAdapter;
import com.soon.karat.retrofit_api_2.Udacity.models.Degree;
import com.soon.karat.retrofit_api_2.Udacity.models.UdacityCatalog;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DegreesActivity extends BaseActivity {

    private static final String TAG = "DegreesActivity";

    // Layout
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_degrees);

        setupWidgets();

        // 1. Build the retrofit by using the BASE_URL and the Converter;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UdacityService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // 2. Instantiate the service as a polymorphism;
        UdacityService service = retrofit.create(UdacityService.class);

        // 3. List the catalog, it is a call for UdacityCatalog;
        Call<UdacityCatalog> requestCatalog = service.listCatalog();

        // 4. Asynchronous take the information from the API.
        requestCatalog.enqueue(new Callback<UdacityCatalog>() {
            @Override
            public void onResponse(Call<UdacityCatalog> call, Response<UdacityCatalog> response) {
                if (!response.isSuccessful()) {
                    Log.i(TAG, "onResponse: " + response.code());
                } else {
                    UdacityCatalog catalog = response.body();
                    if (catalog != null && catalog.getDegrees() != null) {
                        setupRecyclerView(catalog.getDegrees());
                    }
                }
            }

            @Override
            public void onFailure(Call<UdacityCatalog> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t.getMessage());
            }
        });

    }


    private void setupWidgets() {
        mRecyclerView = findViewById(R.id.recycler_view);
    }

    private void setupRecyclerView(List<Degree> degreeList) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mRecyclerView.setAdapter(new DegreesAdapter(degreeList, this));
    }


}
