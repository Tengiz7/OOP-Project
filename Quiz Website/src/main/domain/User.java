package main.domain;

import main.domain.enums.UserStatus;

public class User {
    public User (String username, String password, UserStatus userStatus){
        this.username = username;
        this.password = password;
        this.userStatus = userStatus;
    }

    public User (String username, String password){
        this.username = username;
        this.password = password;
        this.userStatus = UserStatus.NORMAL;
    }

    public User() {

    }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    private String username;

    private String password;

    private UserStatus userStatus;
}
