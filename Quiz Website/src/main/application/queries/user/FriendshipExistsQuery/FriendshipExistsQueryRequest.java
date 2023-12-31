package main.application.queries.user.FriendshipExistsQuery;

import main.mediator.abstractions.IRequest;

public class FriendshipExistsQueryRequest implements IRequest<FriendshipExistsQueryResponse> {
    private String user_1;
    private String user_2;

    public String getUser_1() {
        return user_1;
    }

    public void setUser_1(String user_1) {
        this.user_1 = user_1;
    }

    public String getUser_2() {
        return user_2;
    }

    public void setUser_2(String user_2) {
        this.user_2 = user_2;
    }
}
