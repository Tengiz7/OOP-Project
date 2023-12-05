package main.application.queries.user.ChallengeQuery;

import main.domain.Challenge;
import main.mediator.abstractions.IRequest;

public class ChallengeQueryRequest implements IRequest<ChallengeQueryResponse> {
    private int chalengeId;

    public ChallengeQueryRequest(int chalengeId) {
        this.chalengeId = chalengeId;
    }

    public int getChalengeId() {
        return chalengeId;
    }

    public void setChalengeId(int chalengeId) {
        this.chalengeId = chalengeId;
    }
}
