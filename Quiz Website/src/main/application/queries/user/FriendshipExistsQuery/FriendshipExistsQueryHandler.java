package main.application.queries.user.FriendshipExistsQuery;

import main.application.queries.user.isFriendRequestSent.IsFriendRequestSentResponse;
import main.application.users.IFriendsRepository;
import main.mediator.abstractions.IRequestHandler;

public class FriendshipExistsQueryHandler implements IRequestHandler<FriendshipExistsQueryRequest,FriendshipExistsQueryResponse> {
    private IFriendsRepository iFriendsRepository;

    public FriendshipExistsQueryHandler(IFriendsRepository iFriendsRepository) {
        this.iFriendsRepository = iFriendsRepository;
    }

    @Override
    public FriendshipExistsQueryResponse handle(FriendshipExistsQueryRequest request) {
        FriendshipExistsQueryResponse friendshipExistsQueryResponse = new FriendshipExistsQueryResponse();
        friendshipExistsQueryResponse.setExists(true);
        if(!(iFriendsRepository.friendshipExists(request.getUser_1(), request.getUser_2()) && iFriendsRepository.friendshipExists(request.getUser_2(), request.getUser_1()))){
            friendshipExistsQueryResponse.setExists(false);
        }
        return friendshipExistsQueryResponse;
    }
}
