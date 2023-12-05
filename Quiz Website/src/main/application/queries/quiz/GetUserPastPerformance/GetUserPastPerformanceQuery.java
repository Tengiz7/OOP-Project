package main.application.queries.quiz.GetUserPastPerformance;

import main.mediator.abstractions.IRequest;

import java.util.List;

public class GetUserPastPerformanceQuery implements IRequest<List<GetUserPastPerformanceResponse>> {
    private String username;
    private int quizId;

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
}
