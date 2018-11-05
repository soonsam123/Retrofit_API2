/*
 * Copyright (C) LeafSoon 2018
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential.
 *
 * Written by Soon Sam <karatesoon@gmail.com>
 *
 */

package com.soon.karat.retrofit_api_2.StackOverflow.Interface;

import com.soon.karat.retrofit_api_2.StackOverflow.models.Answer;
import com.soon.karat.retrofit_api_2.StackOverflow.models.Question;
import com.soon.karat.retrofit_api_2.utils.ListWrapper;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface StackOverflowAPI {

    String BASE_URL = "https://api.stackexchange.com";

    // Returns a list of questions
    // 1. The list is ordered in descendant order;
    // 2. Sorted by the number of votes;
    // 3. In the website stackoverflow;
    // 4. With the tag android;
    // 5. And the ones that has body.
    // Note: This will return a List of Questions wrapped by a ListWrapper, to access
    // the list of questions I'll need to call wrapper.items
    @GET("/2.2/questions?order=desc&sort=votes&site=stackoverflow&tagged=android&filter=withbody")
    Call<ListWrapper<Question>> getQuestions();


    // Returns a list of answer from a specific questions by getting the questionId
    // 1. The list is from a question with id {questionId};
    // 2. It is ordered in descendant order;
    // 3. Sorted by the number of votes in the answer;
    // 4. In the website stackoverflow.
    // Note: This will return a List of Answers wrapper by a ListWrapper, to access
    // the list of questions I'll need to call the wrapper.items
    @GET("/2.2/questions/{id}/answers?order=desc&sort=votes&site=stackoverflow")
    Call<ListWrapper<Answer>> getAnswersForQuestion(@Path("id") String questionId);
}
