package main.application.commands.user.RemoveUserCommand;

import main.mediator.abstractions.IRequest;

public class RemoveUserCommandRequest implements IRequest<RemoveUserCommandResponse> {
    public RemoveUserCommandRequest(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userId) {
        this.username = userId;
    }

    private String username;
}
