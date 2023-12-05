package main.servlets;

import main.application.HandlerProvider;
import main.application.queries.user.SearchUsersQuery.SearchUsersQueryRequest;
import main.application.queries.user.SearchUsersQuery.SearchUsersQueryResponse;
import main.mediator.Mediator;
import main.persistence.DbContext;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/searchFriends")
public class SearchFriendsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Mediator mediator = new Mediator(new HandlerProvider(new DbContext()));

        SearchUsersQueryRequest searchUsersQueryRequest = new SearchUsersQueryRequest();
        searchUsersQueryRequest.setUsername("");
        List<SearchUsersQueryResponse> users = mediator.send(searchUsersQueryRequest);
        request.setAttribute("users", users);

        request.getRequestDispatcher("search_friends.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
