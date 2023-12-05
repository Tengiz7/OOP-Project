package main.application.commands.user.RemoveFriendshipCommand;

import main.application.users.IFriendsRepository;
import main.application.users.IUserRepository;
import main.mediator.abstractions.IRequestHandler;

public class RemoveFriendshipCommandHandler implements IRequestHandler<RemoveFriendshipCommandRequest,RemoveFriendshipCommandResponse> {

    IFriendsRepository friendsRepository;
    IUserRepository userRepository;

    public RemoveFriendshipCommandHandler(IFriendsRepository friendsRepository, IUserRepository userRepository) {
        this.friendsRepository = friendsRepository;
        this.userRepository = userRepository;
    }

    @Override
    public RemoveFriendshipCommandResponse handle(RemoveFriendshipCommandRequest request) {
        if(!userRepository.isUsernameTaken(request.getUser()) || !userRepository.isUsernameTaken(request.getOldFriend())){
            throw new RuntimeException("Username doesn't exist");
        }
        friendsRepository.removeFriendship(request.getUser(),request.getOldFriend());
        friendsRepository.removeFriendship(request.getOldFriend(),request.getUser());
        return new RemoveFriendshipCommandResponse();
    }
}
