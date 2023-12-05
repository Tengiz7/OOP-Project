package main.application.queries.user.ChallengeQuery;

import main.application.users.IChallengeRepository;
import main.domain.Challenge;
import main.mediator.abstractions.IRequestHandler;

public class ChallengeQueryRequestHandler implements IRequestHandler<ChallengeQueryRequest, ChallengeQueryResponse> {

    private IChallengeRepository challengeRepository;

    public ChallengeQueryRequestHandler(IChallengeRepository challengeRepository) {
        this.challengeRepository = challengeRepository;
    }

    @Override
    public ChallengeQueryResponse handle(ChallengeQueryRequest request) {
        Challenge challenge = challengeRepository.getChallenge(request.getChalengeId());
        ChallengeQueryResponse response = new ChallengeQueryResponse();
        response.setChallengeID(challenge.getChallengeID());
        response.setSenderUsername(challenge.getSenderUsername());
        response.setReceiverUsername(challenge.getReceiverUsername());
        response.setQuizID(challenge.getQuizID());
        response.setMessage(challenge.getMessage());
        response.setLocalDateTime(challenge.getLocalDateTime());
        return response;
    }
}
