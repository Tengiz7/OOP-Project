package main.application.queries.user.ReceivedChallengesQuery;

import main.domain.Challenge;

import java.util.List;

public class ReceivedChallengesQueryResponse {
    private List<Challenge> receivedChallenges;

    public List<Challenge> getRequests() {
        return receivedChallenges;
    }
    public void setRequests(List<Challenge> requests) {
        this.receivedChallenges = requests;
    }
}
