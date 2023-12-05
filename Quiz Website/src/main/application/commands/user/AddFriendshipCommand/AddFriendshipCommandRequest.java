package main.application.commands.user.AddFriendshipCommand;

import main.mediator.abstractions.IRequest;

import java.time.LocalDateTime;

public class AddFriendshipCommandRequest implements IRequest<AddFriendshipCommandResponse>{
    private String user;
    private String futureFriend;
    private LocalDateTime localDateTime;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getFutureFriend() {
        return futureFriend;
    }

    public void setFutureFriend(String futureFriend) {
        this.futureFriend = futureFriend;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
