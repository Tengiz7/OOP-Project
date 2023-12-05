package main.application.queries.user.isFriendRequestSent;

import main.application.queries.user.UsernameExistsQuery.UsernameExistsQueryResponse;
import main.application.users.IFriendRequestRepository;
import main.mediator.abstractions.IRequestHandler;

public class IsFriendRequestSentHandler implements IRequestHandler<IsFriendRequestSentRequest, IsFriendRequestSentResponse> {
    private IFriendRequestRepository iFriendRequestRepository;

    public IsFriendRequestSentHandler(IFriendRequestRepository iFriendRequestRepository) {
        this.iFriendRequestRepository = iFriendRequestRepository;
    }

    @Override
    public IsFriendRequestSentResponse handle(IsFriendRequestSentRequest request) {
        IsFriendRequestSentResponse isFriendRequestSentResponse = new IsFriendRequestSentResponse();
        isFriendRequestSentResponse.setExists(true);
        if(!iFriendRequestRepository.friendRequestExists(request.getSender(), request.getReceiver())){
            isFriendRequestSentResponse.setExists(false);
        }
        return isFriendRequestSentResponse;
    }
}
