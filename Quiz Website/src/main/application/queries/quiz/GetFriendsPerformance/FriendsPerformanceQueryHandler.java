package main.application.queries.quiz.GetFriendsPerformance;

import main.application.quiz.IQuizAttemptRepository;
import main.application.quiz.IQuizRepository;
import main.application.users.IFriendsRepository;
import main.application.users.IUserRepository;
import main.domain.QuizAttempt;
import main.mediator.abstractions.IRequestHandler;

import java.util.ArrayList;
import java.util.List;

public class FriendsPerformanceQueryHandler implements IRequestHandler<FriendsPerformanceQuery, List<FriendsPerformanceQueryResponse>> {

    private IUserRepository userRepository;
    private IQuizRepository quizRepository;
    private IQuizAttemptRepository quizAttemptRepository;

    public FriendsPerformanceQueryHandler(IUserRepository userRepository, IQuizRepository quizRepository, IQuizAttemptRepository quizAttemptRepository) {
        this.userRepository = userRepository;
        this.quizRepository = quizRepository;
        this.quizAttemptRepository = quizAttemptRepository;
    }

    @Override
    public List<FriendsPerformanceQueryResponse> handle(FriendsPerformanceQuery request) {
        if (!userRepository.isUsernameTaken(request.getUsername())) {
            throw new RuntimeException("User with username " + request.getUsername() + " does not exist");
        }
        if (quizRepository.getQuizById(request.getQuizId()) == null) {
            throw new RuntimeException("Quiz with id " + request.getQuizId() + " does not exist");
        }
        List<QuizAttempt> quizAttempts = quizAttemptRepository.getFriendsQuizAttempts(request.getUsername(), request.getQuizId());
        List<FriendsPerformanceQueryResponse> result = new ArrayList<>();
        for (int i = 0; i < quizAttempts.size(); i++) {
            FriendsPerformanceQueryResponse response = new FriendsPerformanceQueryResponse();
            response.setUsername(quizAttempts.get(i).getUsername());
            response.setScore(quizAttempts.get(i).getScore());
            result.add(response);
        }
        return result;
    }
}
