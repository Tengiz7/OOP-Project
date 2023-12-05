package main.application.queries.user.GetUserQuery;

import main.mediator.abstractions.IRequest;

public class UserQueryRequest implements IRequest<UserQueryResponse> {
    private String username;
    private String password;

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