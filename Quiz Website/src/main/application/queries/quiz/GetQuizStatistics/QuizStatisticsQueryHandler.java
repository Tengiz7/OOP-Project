package main.application.queries.quiz.GetQuizStatistics;

import main.application.quiz.IQuizAttemptRepository;
import main.application.quiz.IQuizRepository;
import main.mediator.abstractions.IRequestHandler;

public class QuizStatisticsQueryHandler implements IRequestHandler<QuizStatisticsQuery, QuizStatisticsQueryResponse> {

    private IQuizRepository quizRepository;
    private IQuizAttemptRepository quizAttemptRepository;

    public QuizStatisticsQueryHandler(IQuizRepository quizRepository, IQuizAttemptRepository quizAttemptRepository) {
        this.quizRepository = quizRepository;
        this.quizAttemptRepository = quizAttemptRepository;
    }

    @Override
    public QuizStatisticsQueryResponse handle(QuizStatisticsQuery request) {
        if (quizRepository.getQuizById(request.getQuizId()) == null) {
            throw new RuntimeException("Quiz with id " + request.getQuizId() + " does not exist");
        }
        QuizStatisticsQueryResponse response = new QuizStatisticsQueryResponse();
        response.setNumberOfTimesTaken(quizAttemptRepository.getNumberOfTimesTaken(request.getQuizId()));
        response.setNumberOfUniqueTimesTaken(quizAttemptRepository.getNumberOfUniqueTimesTaken(request.getQuizId()));
        response.setMaxScore(quizAttemptRepository.getMaxScore(request.getQuizId()));
        response.setAverageScore(quizAttemptRepository.getAverageScore(request.getQuizId()));
        return response;
    }
}
