package main.application.queries.quiz.UserCreatedQuizes;

import main.application.queries.quiz.QuizesResponse;
import main.mediator.abstractions.IRequest;

import java.util.List;

public class UserCreatedQuizesQuery implements IRequest<List<QuizesResponse>> {
    public UserCreatedQuizesQuery(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }


    public void setUserId(String username) {
        this.username = username;
    }

    private String username;
}
