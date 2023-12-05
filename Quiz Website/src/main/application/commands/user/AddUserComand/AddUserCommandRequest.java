package main.application.commands.user.AddUserComand;

import main.domain.enums.UserStatus;
import main.mediator.abstractions.IRequest;

public class AddUserCommandRequest implements IRequest<AddUserCommandResponse> {
    private String username;
    private String password;

    private UserStatus userStatus;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
