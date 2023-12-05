package main.application.queries.user.GetSentFriendRequestsQuery;

import main.application.users.IFriendRequestRepository;
import main.application.users.IUserRepository;
import main.mediator.abstractions.IRequestHandler;

public class SentFriendRequestsQueryHandler implements IRequestHandler<SentFriendRequestsQueryRequest,SentFriendRequestsQueryResponse> {
    private IFriendRequestRepository friendRequestRepository;
    private IUserRepository userRepository;

    public SentFriendRequestsQueryHandler(IFriendRequestRepository friendRequestRepository, IUserRepository userRepository) {
        this.friendRequestRepository = friendRequestRepository;
        this.userRepository = userRepository;
    }

    @Override
    public SentFriendRequestsQueryResponse handle(SentFriendRequestsQueryRequest request) {
        if(!userRepository.isUsernameTaken(request.getSenderUsername())){
            throw new RuntimeException("Sender username doesn't exist");
        }
        SentFriendRequestsQueryResponse sentFriendRequestsQueryResponse = new SentFriendRequestsQueryResponse();
        sentFriendRequestsQueryResponse.setRequests(friendRequestRepository.getSentFriendRequests(request.getSenderUsername()));
        return sentFriendRequestsQueryResponse;
    }
}
