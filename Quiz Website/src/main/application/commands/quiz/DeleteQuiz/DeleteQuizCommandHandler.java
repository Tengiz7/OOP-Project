package main.application.commands.quiz.DeleteQuiz;

import main.application.quiz.IQuizRepository;
import main.mediator.abstractions.IRequestHandler;

public class DeleteQuizCommandHandler implements IRequestHandler<DeleteQuizCommand, DeleteQuizResponse> {
    private IQuizRepository repo;
    public DeleteQuizCommandHandler(IQuizRepository quizRepository) {
        repo = quizRepository;
    }
    @Override
    public DeleteQuizResponse handle(DeleteQuizCommand request) {
        repo.deleteQuiz(request.getId());
        return new DeleteQuizResponse();
    }
}
