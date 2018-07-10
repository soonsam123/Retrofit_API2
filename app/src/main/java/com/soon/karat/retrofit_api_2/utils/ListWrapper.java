package com.soon.karat.retrofit_api_2.utils;

import java.util.List;

public class ListWrapper<T> {
    public List<T> items;
    // This wrapper another list into the ListWrapper
    // Response<ListWrapper<Answer>> response
    // List<Answers> response.body().items
    // The items in the Wrapper is the items of the wrapped list
    // E.g.
    // 1. ListWrapper<Question> wrapper
    // 2. List<Question> questions = wrapper.items
}
