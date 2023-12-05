package main.application.queries.user.UsernameExistsQuery;

import main.mediator.abstractions.IRequest;

public class UsernameExistsQueryRequest implements IRequest<UsernameExistsQueryResponse> {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
