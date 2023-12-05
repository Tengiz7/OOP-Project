package main.application.commands.user.AddUserComand;

import main.application.users.Hasher;
import main.application.users.IUserRepository;
import main.domain.User;
import main.domain.enums.UserStatus;
import main.mediator.abstractions.IRequestHandler;

public class AddUserCommandHandler implements IRequestHandler<AddUserCommandRequest,AddUserCommandResponse> {
    private IUserRepository iUserRepository;

    public AddUserCommandHandler(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    @Override
    public AddUserCommandResponse handle(AddUserCommandRequest request) {
        if(request.getUsername().isEmpty() || request.getPassword().isEmpty()){
            throw new RuntimeException("Username and Password mustn't be empty");
        }
        if(iUserRepository.isUsernameTaken(request.getUsername())){
            throw new RuntimeException("Username " + request.getUsername() + " is already taken!");
        }
        iUserRepository.addUser(new User(request.getUsername(), Hasher.hashPassword(request.getPassword()), UserStatus.NORMAL));
        return new AddUserCommandResponse();
    }
}
