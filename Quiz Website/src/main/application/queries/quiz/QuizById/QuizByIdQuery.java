package main.application.queries.quiz.QuizById;

import main.mediator.abstractions.IRequest;

public class QuizByIdQuery implements IRequest<QuizByIdResponse> {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    public QuizByIdQuery(int id) {
        this.id = id;
    }
}
