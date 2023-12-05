package main.application.commands.user.AddFriendRequestCommand;

import main.application.users.IFriendRequestRepository;
import main.application.users.IUserRepository;
import main.mediator.abstractions.IRequestHandler;

public class AddFriendRequestCommandHandler implements IRequestHandler<AddFriendRequestCommandRequest,AddFriendRequestCommandResponse> {
    private IFriendRequestRepository friendRequestRepository;
    private IUserRepository userRepository;

    public AddFriendRequestCommandHandler(IFriendRequestRepository friendRequestRepository, IUserRepository userRepository) {
        this.friendRequestRepository = friendRequestRepository;
        this.userRepository = userRepository;
    }

    @Override
    public AddFriendRequestCommandResponse handle(AddFriendRequestCommandRequest request) {
        if(request.getSenderUsername().equals(request.getReceiverUsername())){
            throw new RuntimeException("You can't send friend request to yourself");
        }
        if(friendRequestRepository.friendRequestExists(request.getSenderUsername(),request.getReceiverUsername())){
            throw new RuntimeException("Friend request already exists");
        }
        if(!userRepository.isUsernameTaken(request.getSenderUsername()) || !userRepository.isUsernameTaken(request.getReceiverUsername())){
            throw new RuntimeException("Username doesn't exist");
        }
        friendRequestRepository.addFriendRequest(request.getSenderUsername(),request.getReceiverUsername(),request.getMessage());
        return new AddFriendRequestCommandResponse();
    }
}
