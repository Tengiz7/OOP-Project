package main.application.queries.user.SearchUsersQuery;

import main.mediator.abstractions.IRequest;

import java.util.List;

public class SearchUsersQueryRequest implements IRequest<List<SearchUsersQueryResponse>> {
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String username;
}
