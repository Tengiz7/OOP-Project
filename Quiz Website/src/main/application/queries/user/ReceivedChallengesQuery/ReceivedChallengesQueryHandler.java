package main.application.queries.user.ReceivedChallengesQuery;

import main.application.users.IChallengeRepository;
import main.application.users.IUserRepository;
import main.mediator.abstractions.IRequestHandler;

public class ReceivedChallengesQueryHandler implements IRequestHandler<ReceivedChallengesQueryRequest,ReceivedChallengesQueryResponse> {
    private IUserRepository userRepository;
    private IChallengeRepository challengeRepository;

    public ReceivedChallengesQueryHandler(IUserRepository userRepository, IChallengeRepository challengeRepository) {
        this.userRepository = userRepository;
        this.challengeRepository = challengeRepository;
    }

    @Override
    public ReceivedChallengesQueryResponse handle(ReceivedChallengesQueryRequest request) {
        if(!userRepository.isUsernameTaken(request.getReceiverUsername())){
            throw new RuntimeException("Receiver username doesn't exist");
        }
        ReceivedChallengesQueryResponse receivedChallengesQueryResponse = new ReceivedChallengesQueryResponse();
        receivedChallengesQueryResponse.setRequests(challengeRepository.getReceivedChallenges(request.getReceiverUsername()));
        return receivedChallengesQueryResponse;
    }
}
