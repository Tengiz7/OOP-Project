package main.application.queries.quiz.PopularQuizes;

import main.application.queries.quiz.QuizesResponse;
import main.application.quiz.IQuizRepository;
import main.domain.Quiz;
import main.mediator.abstractions.IRequestHandler;

import java.util.ArrayList;
import java.util.List;

public class PopularQuizesHandler implements IRequestHandler<PopularQuizesQuery, List<QuizesResponse>> {

    private IQuizRepository repo;

    public PopularQuizesHandler(IQuizRepository repo) {
        this.repo = repo;
    }
    @Override
    public List<QuizesResponse> handle(PopularQuizesQuery request) {
        List<Quiz> quizList = repo.getPopularQuizes();
        List<QuizesResponse> responses = new ArrayList<>();
        for(Quiz q : quizList) {
            responses.add(map(q));
        }
        return responses;
    }

    private QuizesResponse map(Quiz quiz) {
        QuizesResponse result = new QuizesResponse();
        result.setId(quiz.getId());
        result.setTitle(quiz.getTitle());
        result.setDescription(quiz.getDescription());
        result.setCreatedAt(quiz.getCreatedAt());
        result.setCreatorId(quiz.getCreatorId());
        return result;
    }
}
