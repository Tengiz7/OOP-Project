package main.application.commands.user.RemoveFriendshipCommand;

import main.mediator.abstractions.IRequest;

public class RemoveFriendshipCommandRequest implements IRequest<RemoveFriendshipCommandResponse> {
    private String user;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getOldFriend() {
        return oldFriend;
    }

    public void setOldFriend(String oldFriend) {
        this.oldFriend = oldFriend;
    }

    private String oldFriend;
}
