package main.application.queries.user.GetFriendshipsQuery;

import main.application.users.IFriendsRepository;
import main.application.users.IUserRepository;
import main.mediator.abstractions.IRequestHandler;

public class GetFriendshipsQueryHandler implements IRequestHandler<GetFriendshipsQueryRequest,GetFriendshipsQueryResponse> {
    IFriendsRepository friendsRepository;
    IUserRepository userRepository;

    public GetFriendshipsQueryHandler(IFriendsRepository friendsRepository, IUserRepository userRepository) {
        this.friendsRepository = friendsRepository;
        this.userRepository = userRepository;
    }

    @Override
    public GetFriendshipsQueryResponse handle(GetFriendshipsQueryRequest request) {
        if(!userRepository.isUsernameTaken(request.getUser())){
            throw new RuntimeException("username doesn't exist");
        }
        GetFriendshipsQueryResponse getFriendshipsQueryResponse = new GetFriendshipsQueryResponse();
        getFriendshipsQueryResponse.setFriends(friendsRepository.getFriendships(request.getUser()));
        return getFriendshipsQueryResponse;
    }
}
