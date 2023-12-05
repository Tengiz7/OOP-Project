package main.application.queries.quiz.FriendCreatedQuizes;

import main.application.queries.quiz.QuizesResponse;
import main.mediator.abstractions.IRequest;

import java.util.List;

public class FriendCreatedQuizesQuery implements IRequest<List<QuizesResponse>> {
    public FriendCreatedQuizesQuery(String username) {
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
