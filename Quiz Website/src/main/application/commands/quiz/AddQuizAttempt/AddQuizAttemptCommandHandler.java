package main.application.commands.quiz.AddQuizAttempt;

import main.application.quiz.IQuizAttemptRepository;
import main.application.quiz.IQuizRepository;
import main.application.users.IUserRepository;
import main.domain.QuizAttempt;
import main.mediator.abstractions.IRequestHandler;

public class AddQuizAttemptCommandHandler implements IRequestHandler<AddQuizAttemptRequest, AddQuizAttemptResponse> {

    private IQuizRepository quizRepo;
    private IUserRepository userRepo;
    private IQuizAttemptRepository repo;

    public AddQuizAttemptCommandHandler(IQuizRepository quizRepo, IUserRepository userRepo, IQuizAttemptRepository repository) {
        this.quizRepo = quizRepo;
        this.userRepo = userRepo;
        this.repo = repository;
    }

    @Override
    public AddQuizAttemptResponse handle(AddQuizAttemptRequest request) {
        if (quizRepo.getQuizById(request.getQuizId()) == null) {
            throw new RuntimeException("Quiz does not exist");
        }
        if (!userRepo.isUsernameTaken(request.getUsername())) {
            throw new RuntimeException("User does not exist");
        }
        QuizAttempt attempt = new QuizAttempt();
        attempt.setQuizId(request.getQuizId());
        attempt.setUsername(request.getUsername());
        attempt.setScore(request.getScore());
        repo.addQuizAttempt(attempt);
        return new AddQuizAttemptResponse();
    }
}
