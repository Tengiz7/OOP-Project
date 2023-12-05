package main.application.commands.user.AddMessageCommand;

import main.application.users.IFriendRequestRepository;
import main.application.users.IFriendsRepository;
import main.application.users.IMessagesRepository;
import main.application.users.IUserRepository;
import main.mediator.abstractions.IRequestHandler;

public class AddMessageCommandHandler implements IRequestHandler<AddMessageCommandRequest,AddMessageCommandResponse> {
    private IFriendsRepository friendsRepository;
    private IUserRepository userRepository;
    private IMessagesRepository messagesRepository;

    public AddMessageCommandHandler(IFriendsRepository friendsRepository, IUserRepository userRepository, IMessagesRepository messagesRepository) {
        this.friendsRepository = friendsRepository;
        this.userRepository = userRepository;
        this.messagesRepository = messagesRepository;
    }

    @Override
    public AddMessageCommandResponse handle(AddMessageCommandRequest request) {
        if(!userRepository.isUsernameTaken(request.getSenderUsername()) || !userRepository.isUsernameTaken(request.getReceiverUsername())){
            throw new RuntimeException("Username doesn't exist");
        }
        if(!friendsRepository.friendshipExists(request.getSenderUsername(), request.getReceiverUsername())){
            throw new RuntimeException("Users are not friends");
        }
        messagesRepository.sendMessage(request.getSenderUsername(), request.getReceiverUsername(), request.getMessage(), request.getLocalDateTime());
        return new AddMessageCommandResponse();
    }
}
