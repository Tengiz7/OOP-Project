package main.application.queries.quiz.GetFriendsPerformance;

import main.mediator.abstractions.IRequest;
import main.mediator.abstractions.IRequestHandler;

import java.util.List;

public class FriendsPerformanceQuery implements IRequest<List<FriendsPerformanceQueryResponse>> {
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    private String username;
    private int quizId;

}
