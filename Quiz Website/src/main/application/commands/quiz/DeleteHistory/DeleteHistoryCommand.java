package main.application.commands.quiz.DeleteHistory;

import main.mediator.abstractions.IRequest;

public class DeleteHistoryCommand implements IRequest<DeleteHistoryResponse> {
    private int id;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
