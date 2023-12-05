package main.application.queries.quiz.GetQuizStatistics;

import main.mediator.abstractions.IRequest;

public class QuizStatisticsQuery implements IRequest<QuizStatisticsQueryResponse> {
    private int quizId;

    public QuizStatisticsQuery(int quizId) {
        this.quizId = quizId;
    }

    public int getQuizId() {
        return quizId;
    }
}
