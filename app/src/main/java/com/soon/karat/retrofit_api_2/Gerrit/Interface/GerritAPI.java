package com.soon.karat.retrofit_api_2.Gerrit.Interface;

import com.soon.karat.retrofit_api_2.Gerrit.models.Change;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GerritAPI {

    public static final String BASE_URL = "https://git.eclipse.org/r/";

    @GET("changes/")
    Call<List<Change>> loadChanges(@Query("q") String status);

    @GET("changes/")
    Call<List<Change>> loadChangesByInsertions(@Query("q") int insertions);

    @GET("changes/")
    Call<List<Change>> loadChangesById(@Query("q") String id);

    @GET("changes/")
    Call<List<Change>> loadChangesByMergeable(@Query("q") boolean mergeable);
}
