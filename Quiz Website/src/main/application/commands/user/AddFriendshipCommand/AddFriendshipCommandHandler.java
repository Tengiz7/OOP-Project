package main.application.commands.user.AddFriendshipCommand;

import main.application.users.IFriendRequestRepository;
import main.application.users.IFriendsRepository;
import main.application.users.IUserRepository;
import main.mediator.abstractions.IRequestHandler;

public class AddFriendshipCommandHandler implements IRequestHandler<AddFriendshipCommandRequest, AddFriendshipCommandResponse> {

    private IFriendRequestRepository friendRequestRepository;
    private IFriendsRepository friendsRepository;
    private IUserRepository userRepository;

    public AddFriendshipCommandHandler(IFriendRequestRepository friendRequestRepository, IFriendsRepository friendsRepository, IUserRepository userRepository) {
        this.friendRequestRepository = friendRequestRepository;
        this.friendsRepository = friendsRepository;
        this.userRepository = userRepository;
    }

    @Override
    public AddFriendshipCommandResponse handle(AddFriendshipCommandRequest request) {
        if (friendsRepository.friendshipExists(request.getUser(), request.getFutureFriend())) {
            throw new RuntimeException("Friend already exists");
        }
        if (!friendRequestRepository.friendRequestExists(request.getFutureFriend(), request.getUser())) {
            throw new RuntimeException("Friend request doesn't exists");
        }
        if (!userRepository.isUsernameTaken(request.getUser()) || !userRepository.isUsernameTaken(request.getFutureFriend())) {
            throw new RuntimeException("username doesn't exist");
        }

        friendsRepository.addFriend(request.getUser(), request.getFutureFriend(), request.getLocalDateTime());
        friendsRepository.addFriend(request.getFutureFriend(), request.getUser(), request.getLocalDateTime());
        friendRequestRepository.removeFriendRequest(request.getFutureFriend(), request.getUser());
        AddFriendshipCommandResponse addFriendshipCommandResponse = new AddFriendshipCommandResponse();
        addFriendshipCommandResponse.setAdded(true);
        return addFriendshipCommandResponse;
    }
}
