package main.application.commands.user.SendChallengeCommand;

import main.application.users.IChallengeRepository;
import main.application.users.IFriendsRepository;
import main.application.users.IMessagesRepository;
import main.application.users.IUserRepository;
import main.mediator.abstractions.IRequestHandler;

public class SendChallengeCommandHandler implements IRequestHandler<SendChallengeCommandRequest,SendChallengeCommandResponse> {
    private IFriendsRepository friendsRepository;
    private IUserRepository userRepository;
    private IChallengeRepository challengeRepository;

    public SendChallengeCommandHandler(IFriendsRepository friendsRepository, IUserRepository userRepository, IChallengeRepository challengeRepository) {
        this.friendsRepository = friendsRepository;
        this.userRepository = userRepository;
        this.challengeRepository = challengeRepository;
    }

    @Override
    public SendChallengeCommandResponse handle(SendChallengeCommandRequest request) {
        if(!userRepository.isUsernameTaken(request.getSenderName()) && !userRepository.isUsernameTaken(request.getReceiverName())){
            throw new RuntimeException("Username doesn't exist");
        }
        if(!friendsRepository.friendshipExists(request.getSenderName(), request.getReceiverName())){
            throw new RuntimeException("Users are not friends");
        }
        challengeRepository.addChallenge(request.getSenderName(), request.getReceiverName(), request.getQuizID(), request.getMessage(), request.getLocalDateTime());
        return new SendChallengeCommandResponse();
    }
}
