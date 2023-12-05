package main.application.queries.quiz.FriendAttemptedQuizes;

import main.mediator.abstractions.IRequest;

import java.util.List;

public class FriendAttemptedQuizesQuery implements IRequest<List<FriendAttemptedQuizesResponse>> {
    public FriendAttemptedQuizesQuery(String username) {
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
