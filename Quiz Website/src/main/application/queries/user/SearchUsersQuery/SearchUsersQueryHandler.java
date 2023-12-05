package main.application.queries.user.SearchUsersQuery;

import main.application.users.IUserRepository;
import main.domain.User;
import main.mediator.abstractions.IRequestHandler;

import java.util.ArrayList;
import java.util.List;

public class SearchUsersQueryHandler implements IRequestHandler<SearchUsersQueryRequest, List<SearchUsersQueryResponse>> {
    private IUserRepository repo;
    public SearchUsersQueryHandler(IUserRepository repo) {
        this.repo = repo;
    }
    @Override
    public List<SearchUsersQueryResponse> handle(SearchUsersQueryRequest request) {
        List<User> userList = repo.search(request.getUsername());
        List<SearchUsersQueryResponse> responses = new ArrayList<>();
        for(User user : userList){
            responses.add(map(user));
        }
        return responses;
    }

    private SearchUsersQueryResponse map(User user) {
        SearchUsersQueryResponse result = new SearchUsersQueryResponse();
        result.setUsername(user.getUsername());
        result.setPassword(user.getPassword());
        result.setUserStatus(user.getUserStatus());
        return result;
    }
}
