package main.application.queries.quiz.QuizById;

import main.application.quiz.IQuizRepository;
import main.application.quiz.QuestionFactory;
import main.domain.Question;
import main.domain.Quiz;
import main.mediator.abstractions.IRequestHandler;

public class QuizByIdQueryHandler implements IRequestHandler<QuizByIdQuery, QuizByIdResponse> {

    private IQuizRepository repo;

    public QuizByIdQueryHandler(IQuizRepository repo) {
        this.repo = repo;
    }

    @Override
    public QuizByIdResponse handle(QuizByIdQuery request) {
        Quiz q = repo.getQuizById(request.getId());
        if (q == null) {
            throw new RuntimeException("No quiz with given Id found");
        }
        return map(q);
    }

    private QuizByIdResponse map(Quiz q) {
        QuizByIdResponse response = new QuizByIdResponse(q.getId(), q.getTitle(), q.getDescription(), q.getCreatedAt(), q.getCreatorId());
        for(Question question : q.getQuestions()) {
            response.addQuestion(QuestionFactory.getQuestionForType(question.getType(), question.getId(), question.getQuestion(), question.getAnswer(), question.getImageSource()));
        }
        return response;
    }
}
