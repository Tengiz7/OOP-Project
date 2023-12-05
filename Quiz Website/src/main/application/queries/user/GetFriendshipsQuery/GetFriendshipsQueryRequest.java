package main.application.queries.user.GetFriendshipsQuery;

import main.mediator.abstractions.IRequest;

public class GetFriendshipsQueryRequest implements IRequest<GetFriendshipsQueryResponse> {
    private String user;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
