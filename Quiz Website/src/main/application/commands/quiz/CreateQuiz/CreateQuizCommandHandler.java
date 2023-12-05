package main.application.commands.quiz.CreateQuiz;

import main.application.quiz.IQuestionRepository;
import main.application.quiz.IQuizRepository;
import main.application.users.IUserRepository;
import main.domain.Question;
import main.domain.Quiz;
import main.mediator.Nully;
import main.mediator.abstractions.IRequestHandler;

public class CreateQuizCommandHandler implements IRequestHandler<CreateQuizCommand, CreateQuizCommandResponse> {

    private IQuizRepository quizRepo;
    private IUserRepository userRepo;
    private IQuestionRepository questionRepo;

    public CreateQuizCommandHandler(IQuizRepository quizRepository, IUserRepository userRepo, IQuestionRepository questionRepository) {
        this.quizRepo = quizRepository;
        this.userRepo = userRepo;
        this.questionRepo = questionRepository;
    }

    @Override
    public CreateQuizCommandResponse handle(CreateQuizCommand request) {
        if (!userRepo.isUsernameTaken(request.getCreator())) {
            throw new RuntimeException("User with username " + request.getCreator() + " does not exist");
        }

        CreateQuizCommandResponse response = new CreateQuizCommandResponse();
        Quiz q = new Quiz();
        q.setCreatorId(request.getCreator());
        q.setTitle(request.getTitle());
        q.setDescription(request.getDescription());
        q.setCreatedAt(request.getCreatedAt());

        response.setQuizId(quizRepo.addQuiz(q));
        if (response.getQuizId() == -1) {
            throw new RuntimeException("Something happened while adding your quiz please try again later");
        }

        for(CreateQuestionCommand questionCommand : request.getQuestions()) {
            questionRepo.addQuestion(new Question(response.getQuizId(), questionCommand.getQuestion(), questionCommand.getAnswer(), questionCommand.getImgSource(), questionCommand.getType()));
        }
        return response;
    }
}
