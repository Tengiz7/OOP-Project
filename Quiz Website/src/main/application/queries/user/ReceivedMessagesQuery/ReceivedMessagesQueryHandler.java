package main.application.queries.user.ReceivedMessagesQuery;

import main.application.users.IMessagesRepository;
import main.application.users.IUserRepository;
import main.mediator.abstractions.IRequestHandler;

public class ReceivedMessagesQueryHandler implements IRequestHandler<ReceivedMessagesQueryRequest,ReceivedMessagesQueryResponse> {
    private IMessagesRepository messagesRepository;
    private IUserRepository userRepository;

    public ReceivedMessagesQueryHandler(IMessagesRepository messagesRepository, IUserRepository userRepository) {
        this.messagesRepository = messagesRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ReceivedMessagesQueryResponse handle(ReceivedMessagesQueryRequest request) {
        if(!userRepository.isUsernameTaken(request.getReceiverUsername())){
            throw new RuntimeException("Receiver username doesn't exist");
        }
        ReceivedMessagesQueryResponse receivedMessagesQueryResponse = new ReceivedMessagesQueryResponse();
        receivedMessagesQueryResponse.setMessages(messagesRepository.getReceivedMessages(request.getReceiverUsername()));
        return receivedMessagesQueryResponse;
    }
}
