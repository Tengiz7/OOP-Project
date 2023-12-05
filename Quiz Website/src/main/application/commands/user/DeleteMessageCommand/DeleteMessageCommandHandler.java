package main.application.commands.user.DeleteMessageCommand;

import main.application.users.IMessagesRepository;
import main.mediator.abstractions.IRequestHandler;

public class DeleteMessageCommandHandler implements IRequestHandler<DeleteMessageCommandRequest,DeleteMessageCommandResponse> {
    private IMessagesRepository messagesRepository;

    public DeleteMessageCommandHandler(IMessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
    }

    @Override
    public DeleteMessageCommandResponse handle(DeleteMessageCommandRequest request) {
        if(!messagesRepository.messageExists(request.getId())){
            throw new RuntimeException("message doesn't exist");
        }

        messagesRepository.deleteMessage(request.getId());
        return new DeleteMessageCommandResponse();
    }
}
