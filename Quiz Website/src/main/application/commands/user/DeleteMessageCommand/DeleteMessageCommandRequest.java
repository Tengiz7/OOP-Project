package main.application.commands.user.DeleteMessageCommand;

import main.mediator.abstractions.IRequest;

public class DeleteMessageCommandRequest implements IRequest<DeleteMessageCommandResponse> {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
