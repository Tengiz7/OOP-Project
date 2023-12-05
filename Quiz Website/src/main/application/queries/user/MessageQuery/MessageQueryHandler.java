package main.application.queries.user.MessageQuery;

import main.application.users.IMessagesRepository;
import main.domain.Message;
import main.mediator.abstractions.IRequestHandler;

public class MessageQueryHandler implements IRequestHandler<MessageQueryRequest, MessageQueryResponse> {

    private IMessagesRepository messagesRepository;

    public MessageQueryHandler(IMessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
    }

    @Override
    public MessageQueryResponse handle(MessageQueryRequest request) {
        Message message = messagesRepository.getMessage(request.getMessageId());
        if (message != null) {
            MessageQueryResponse response = new MessageQueryResponse();
            response.setId(message.getId());
            response.setLocalDateTime(message.getLocalDateTime());
            response.setMessage(message.getMessage());
            response.setReceiverUsername(message.getReceiverUsername());
            response.setSenderUsername(message.getSenderUsername());
            return response;
        }
        return null;
    }
}
