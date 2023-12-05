package main.application.queries.user.GetReceivedFriendRequestsQuery;

import main.application.users.IFriendRequestRepository;
import main.application.users.IUserRepository;
import main.mediator.abstractions.IRequestHandler;

public class ReceivedFriendRequestsQueryHandler implements IRequestHandler<ReceivedFriendRequestsQueryRequest,ReceivedFriendRequestsQueryResponse> {
    private IFriendRequestRepository friendRequestRepository;
    private IUserRepository userRepository;

    public ReceivedFriendRequestsQueryHandler(IFriendRequestRepository friendRequestRepository, IUserRepository userRepository) {
        this.friendRequestRepository = friendRequestRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ReceivedFriendRequestsQueryResponse handle(ReceivedFriendRequestsQueryRequest request) {

        if(!userRepository.isUsernameTaken(request.getReceiverUsername())){
            throw new RuntimeException("Receiver username doesn't exist");
        }
        ReceivedFriendRequestsQueryResponse friendRequestsQueryResponse = new ReceivedFriendRequestsQueryResponse();
        friendRequestsQueryResponse.setRequests(friendRequestRepository.getReceivedFriendRequests(request.getReceiverUsername()));
        return  friendRequestsQueryResponse;
    }
}
