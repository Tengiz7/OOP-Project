package main.application.commands.user.ChangeUserRoleCommand;

import main.domain.enums.UserStatus;
import main.mediator.abstractions.IRequest;

public class ChangeUserRoleCommandRequest implements IRequest<ChangeUserRoleCommandResponse> {
    public ChangeUserRoleCommandRequest(String username, UserStatus userStatus) {
        this.username = username;
        this.userStatus = userStatus;
    }

    public UserStatus getRole() {
        return userStatus;
    }
    public void setRole(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String username;

    private UserStatus userStatus;

}
