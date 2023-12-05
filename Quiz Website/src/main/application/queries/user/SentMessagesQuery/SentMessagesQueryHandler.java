package main.application.queries.user.SentMessagesQuery;

import main.application.users.IMessagesRepository;
import main.application.users.IUserRepository;
import main.mediator.abstractions.IRequestHandler;

public class SentMessagesQueryHandler implements IRequestHandler<SentMessagesQueryRequest,SentMessagesQueryResponse> {
    private IMessagesRepository messagesRepository;
    private IUserRepository userRepository;

    public SentMessagesQueryHandler(IMessagesRepository messagesRepository, IUserRepository userRepository) {
        this.messagesRepository = messagesRepository;
        this.userRepository = userRepository;
    }

    @Override
    public SentMessagesQueryResponse handle(SentMessagesQueryRequest request) {
        if(!userRepository.isUsernameTaken(request.getSenderUsername())){
            throw new RuntimeException("Sender username doesn't exist");
        }
        SentMessagesQueryResponse sentMessagesQueryResponse = new SentMessagesQueryResponse();
        sentMessagesQueryResponse.setMessages(messagesRepository.getSentMessages(request.getSenderUsername()));
        return sentMessagesQueryResponse;
    }
}
