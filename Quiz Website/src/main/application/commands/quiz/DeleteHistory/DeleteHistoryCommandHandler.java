package main.application.commands.quiz.DeleteHistory;

import main.application.quiz.IQuizAttemptRepository;
import main.mediator.abstractions.IRequestHandler;

public class DeleteHistoryCommandHandler implements IRequestHandler<DeleteHistoryCommand, DeleteHistoryResponse> {
    private IQuizAttemptRepository repo;
    public DeleteHistoryCommandHandler(IQuizAttemptRepository quizAttemptRepository) {
        repo = quizAttemptRepository;
    }
    @Override
    public DeleteHistoryResponse handle(DeleteHistoryCommand request) {
        repo.deleteHistory(request.getId());
        return new DeleteHistoryResponse();
    }
}
