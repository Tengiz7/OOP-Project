package main.application.queries.quiz.NewQuizes;

import main.application.quiz.IQuizRepository;
import main.domain.Quiz;
import main.mediator.abstractions.IRequestHandler;

import java.util.ArrayList;
import java.util.List;

public class NewQuizesQueryHandler implements IRequestHandler<NewQuizesQuery, List<NewQuizesResponse>> {

    private IQuizRepository repo;

    public NewQuizesQueryHandler(IQuizRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<NewQuizesResponse> handle(NewQuizesQuery request) {
        List<Quiz> quizList = repo.getNewQuizes();
        List<NewQuizesResponse> responses = new ArrayList<>();
        for(Quiz q : quizList) {
            responses.add(map(q));
        }
        return responses;
    }

    private NewQuizesResponse map(Quiz quiz) {
        NewQuizesResponse result = new NewQuizesResponse();
        result.setId(quiz.getId());
        result.setTitle(quiz.getTitle());
        result.setDescription(quiz.getDescription());
        result.setCreatedAt(quiz.getCreatedAt());
        result.setCreatorId(quiz.getCreatorId());
        return result;
    }
}
