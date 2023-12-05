package main.application.queries.user.GetUserQuery;

import main.domain.enums.UserStatus;

public class UserQueryResponse {
    private String username;
    private UserStatus userStatus;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }
}