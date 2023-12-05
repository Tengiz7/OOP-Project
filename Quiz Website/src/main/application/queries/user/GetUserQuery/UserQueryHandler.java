package main.application.queries.user.GetUserQuery;

import main.application.quiz.IQuizRepository;
import main.application.users.Hasher;
import main.application.users.IUserRepository;
import main.domain.User;
import main.infrastructure.UserRepository;
import main.mediator.abstractions.IRequestHandler;

public class UserQueryHandler implements IRequestHandler<UserQueryRequest, UserQueryResponse> {
    private IUserRepository repo;

    public UserQueryHandler(IUserRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserQueryResponse handle(UserQueryRequest request) {
        if(request.getUsername().isEmpty() || request.getPassword().isEmpty()){
            throw new RuntimeException("Username and Password mustn't be empty");
        }
        User user = repo.getUser(request.getUsername(), Hasher.hashPassword(request.getPassword()));
        if (user == null) {
//            throw new RuntimeException("User with given credentials not found");
            return null;
        }
        UserQueryResponse userQueryResponse = new UserQueryResponse();
        userQueryResponse.setUsername(user.getUsername());
        userQueryResponse.setUserStatus(user.getUserStatus());
        return userQueryResponse;
    }
}