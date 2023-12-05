package main.application.queries.user.ReceivedChallengesQuery;

import main.mediator.abstractions.IRequest;

public class ReceivedChallengesQueryRequest implements IRequest<ReceivedChallengesQueryResponse> {
    private String receiverUsername;

    public String getReceiverUsername() {
        return receiverUsername;
    }

    public void setReceiverUsername(String receiverUsername) {
        this.receiverUsername = receiverUsername;
    }
}
