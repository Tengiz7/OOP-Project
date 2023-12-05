package main.application.commands.user.DeleteChallengeCommand;

import main.mediator.abstractions.IRequest;

public class DeleteChallengeCommandRequest implements IRequest<DeleteChallengeCommandResponse> {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
