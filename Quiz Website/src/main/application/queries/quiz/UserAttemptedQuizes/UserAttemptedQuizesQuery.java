package main.application.queries.quiz.UserAttemptedQuizes;

import main.application.queries.quiz.QuizesResponse;
import main.mediator.abstractions.IRequest;

import java.util.List;

public class UserAttemptedQuizesQuery implements IRequest<List<QuizesResponse>> {

    public UserAttemptedQuizesQuery(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String username;

}
