package com.soon.karat.retrofit_api_2.utils;

import com.soon.karat.retrofit_api_2.StackOverflow.models.Answer;
import com.soon.karat.retrofit_api_2.StackOverflow.models.Question;

import java.util.ArrayList;
import java.util.List;

public class FakeDataProvider {

    public static List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>();

        for (int i = 0; i<10; i++) {
            Question question = new Question();
            question.questionId = String.valueOf(i);
            question.title = "Title " + String.valueOf(i);
            question.body = "Body " + String.valueOf(i);
            questions.add(question);
        }

        return questions;
    }

    public static List<Answer> getAnswers() {
        List<Answer> answers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Answer answer = new Answer();
            answer.answerId = i;
            answer.accepted = false;
            answer.score = i;
            answers.add(answer);
        }
        return answers;
    }

}
