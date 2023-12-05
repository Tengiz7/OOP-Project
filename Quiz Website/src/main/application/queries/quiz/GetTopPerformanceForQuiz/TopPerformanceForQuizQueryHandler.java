package main.application.queries.quiz.GetTopPerformanceForQuiz;

import main.application.quiz.IQuizAttemptRepository;
import main.application.quiz.IQuizRepository;
import main.domain.QuizAttempt;
import main.mediator.abstractions.IRequestHandler;

import java.util.List;

public class TopPerformanceForQuizQueryHandler implements IRequestHandler<TopPerformanceForQuizQuery, List<TopPerformanceForQuizQueryResponse>> {

    private IQuizRepository quizRepository;
    private IQuizAttemptRepository quizAttemptRepository;

    public TopPerformanceForQuizQueryHandler(IQuizRepository quizRepository, IQuizAttemptRepository quizAttemptRepository) {
        this.quizRepository = quizRepository;
        this.quizAttemptRepository = quizAttemptRepository;
    }

    @Override
    public List<TopPerformanceForQuizQueryResponse> handle(TopPerformanceForQuizQuery request) {
        if (quizRepository.getQuizById(request.getQuizId()) == null) {
            throw new IllegalArgumentException("Quiz does not exist");
        }
        List<QuizAttempt> quizAttempts = quizAttemptRepository.getTopQuizAttemptsByQuiz(request.getQuizId());
        List<TopPerformanceForQuizQueryResponse> result = new java.util.ArrayList<>();
        for (QuizAttempt quizAttempt : quizAttempts) {
            TopPerformanceForQuizQueryResponse response = new TopPerformanceForQuizQueryResponse();
            response.setUsername(quizAttempt.getUsername());
            response.setScore(quizAttempt.getScore());
            result.add(response);
        }
        return result;
    }
}
