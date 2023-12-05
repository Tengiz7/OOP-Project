package main.application.commands.user.ChangeUserRoleCommand;

import main.application.commands.user.RemoveUserCommand.RemoveUserCommandResponse;
import main.application.users.IUserRepository;
import main.mediator.abstractions.IRequestHandler;

public class ChangeUserRoleCommandHandler implements IRequestHandler<ChangeUserRoleCommandRequest, ChangeUserRoleCommandResponse> {
    private IUserRepository repo;

    public ChangeUserRoleCommandHandler(IUserRepository iUserRepository) {
        repo = iUserRepository;
    }
    @Override
    public ChangeUserRoleCommandResponse handle(ChangeUserRoleCommandRequest request) {
        if(!repo.isUsernameTaken(request.getUsername())){
            throw new RuntimeException("No user found with this ID");
        }
        repo.changeUserRole(request.getUsername(),request.getRole());
        return new ChangeUserRoleCommandResponse();
    }
}
