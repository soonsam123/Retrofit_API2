/*
 * Copyright (C) LeafSoon 2018
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential.
 *
 * Written by Soon Sam <karatesoon@gmail.com>
 *
 */

package com.soon.karat.retrofit_api_2.Github.Interface;

import com.soon.karat.retrofit_api_2.Github.models.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GitHubService {

    public static final String BASE_URL = "https://api.github.com/";

    // 1. Make a call;
    // 2. Where {user} will depend on what user we will type in;
    // 3. E.g. listRepos("soonsam123")

    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);

    @GET("users/{user}/repos")
    Call<List<Repo>> listReposById(@Path("user") String user, @Query("id") int id);

}
