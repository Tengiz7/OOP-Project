package main.application.commands.quiz.DeleteQuiz;

import main.mediator.abstractions.IRequest;

public class DeleteQuizCommand implements IRequest<DeleteQuizResponse> {
    private int id;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
