package main.application.commands.user.RemoveUserCommand;

import main.application.commands.user.AddUserComand.AddUserCommandRequest;
import main.application.commands.user.AddUserComand.AddUserCommandResponse;
import main.application.users.IUserRepository;
import main.mediator.abstractions.IRequestHandler;

public class RemoveUserCommandHandler implements IRequestHandler<RemoveUserCommandRequest, RemoveUserCommandResponse> {

    private IUserRepository repo;

    public RemoveUserCommandHandler(IUserRepository iUserRepository) {
        repo = iUserRepository;
    }
    @Override
    public RemoveUserCommandResponse handle(RemoveUserCommandRequest request) {
        if(!repo.isUsernameTaken(request.getUsername())){
            throw new RuntimeException("No user found with this ID");
        }
        repo.removeUser(request.getUsername());
        return new RemoveUserCommandResponse();
    }
}
