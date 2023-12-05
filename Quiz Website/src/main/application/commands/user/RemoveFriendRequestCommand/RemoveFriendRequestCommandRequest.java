package main.application.commands.user.RemoveFriendRequestCommand;

import main.mediator.abstractions.IRequest;

public class RemoveFriendRequestCommandRequest implements IRequest<RemoveFriendRequestCommandResponse> {
    private String senderUsername;
    private String receiverUsername;

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public String getReceiverUsername() {
        return receiverUsername;
    }

    public void setReceiverUsername(String receiverUsername) {
        this.receiverUsername = receiverUsername;
    }
}
