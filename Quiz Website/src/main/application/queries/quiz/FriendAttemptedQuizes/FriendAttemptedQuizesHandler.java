package main.application.queries.quiz.FriendAttemptedQuizes;

import main.application.quiz.IQuizAttemptRepository;
import main.application.quiz.IQuizRepository;
import main.application.users.IUserRepository;
import main.domain.QuizAttempt;
import main.mediator.abstractions.IRequestHandler;

import java.util.ArrayList;
import java.util.List;

public class FriendAttemptedQuizesHandler implements IRequestHandler<FriendAttemptedQuizesQuery, List<FriendAttemptedQuizesResponse>> {
    private IQuizAttemptRepository repo;
    private IQuizRepository quizRepository;
    private IUserRepository userRepository;

    public FriendAttemptedQuizesHandler(IQuizAttemptRepository repo, IQuizRepository quizRepository, IUserRepository userRepository) {
        this.repo = repo;
        this.quizRepository = quizRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<FriendAttemptedQuizesResponse> handle(FriendAttemptedQuizesQuery request) {
        List<QuizAttempt> quizAttemptList = repo.getQuizzesAttemptedByFriends(request.getUsername());
        List<FriendAttemptedQuizesResponse> responses = new ArrayList<>();
        for(QuizAttempt qa : quizAttemptList) {
            responses.add(map(qa, quizRepository.getQuizById(qa.getQuizId()).getTitle(), qa.getUsername()));
        }
        return responses;
    }

    private FriendAttemptedQuizesResponse map(QuizAttempt quizAttempt, String quizName, String username) {
        FriendAttemptedQuizesResponse result = new FriendAttemptedQuizesResponse();
        result.setId(quizAttempt.getId());
        result.setQuizId(quizAttempt.getQuizId());
        result.setQuizName(quizName);
        result.setUsername(username);
        result.setScore(quizAttempt.getScore());
        return result;
    }
}
