package main.application.queries.user.UsernameExistsQuery;

import main.application.users.IUserRepository;
import main.mediator.abstractions.IRequestHandler;

public class UsernameExistsQueryHandler implements IRequestHandler<UsernameExistsQueryRequest,UsernameExistsQueryResponse> {
    private IUserRepository userRepository;

    public UsernameExistsQueryHandler(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UsernameExistsQueryResponse handle(UsernameExistsQueryRequest request) {
        UsernameExistsQueryResponse usernameExistsQueryResponse = new UsernameExistsQueryResponse();
        usernameExistsQueryResponse.setExists(true);
        if(!userRepository.isUsernameTaken(request.getUsername())){
            usernameExistsQueryResponse.setExists(false);
        }
        return usernameExistsQueryResponse;
    }
}
