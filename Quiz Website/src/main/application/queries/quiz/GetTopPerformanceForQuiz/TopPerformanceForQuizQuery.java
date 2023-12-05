package main.application.queries.quiz.GetTopPerformanceForQuiz;

import main.mediator.abstractions.IRequest;

import java.util.List;

public class TopPerformanceForQuizQuery implements IRequest<List<TopPerformanceForQuizQueryResponse>> {
    public int quizId;

    public TopPerformanceForQuizQuery(int quizId) {
        this.quizId = quizId;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }
}
