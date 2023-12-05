package main.application.commands.user.RemoveFriendRequestCommand;

import main.application.users.IFriendRequestRepository;
import main.application.users.IUserRepository;
import main.mediator.abstractions.IRequestHandler;

public class RemoveFriendRequestCommandHandler implements IRequestHandler<RemoveFriendRequestCommandRequest,RemoveFriendRequestCommandResponse> {
    private IFriendRequestRepository friendRequestRepository;
    private IUserRepository userRepository;

    public RemoveFriendRequestCommandHandler(IFriendRequestRepository friendRequestRepository, IUserRepository userRepository) {
        this.friendRequestRepository = friendRequestRepository;
        this.userRepository = userRepository;
    }

    @Override
    public RemoveFriendRequestCommandResponse handle(RemoveFriendRequestCommandRequest request) {
        if(!friendRequestRepository.friendRequestExists(request.getSenderUsername(), request.getReceiverUsername())){
            throw new RuntimeException("Friend request doesn't exist");
        }
        if (!userRepository.isUsernameTaken(request.getSenderUsername()) || !userRepository.isUsernameTaken(request.getReceiverUsername())) {
            throw new RuntimeException("username doesn't exist");
        }
        friendRequestRepository.removeFriendRequest(request.getSenderUsername(),request.getReceiverUsername());
        return new RemoveFriendRequestCommandResponse();
    }
}
