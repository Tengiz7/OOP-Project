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

@WebServlet("/adminSearchUsers")
public class AdminSearchUsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userStatus = JWTHelper.getDataFromToken((String) request.getSession().getAttribute("Authorization")).role();

        if (!userStatus.equals("ADMIN")) {
            throw new RuntimeException("Unauthorized access");
        }

        Mediator mediator = new Mediator(new HandlerProvider(new DbContext()));

        SearchUsersQueryRequest searchUsersQueryRequest = new SearchUsersQueryRequest();
        searchUsersQueryRequest.setUsername("");
        List<SearchUsersQueryResponse> users = mediator.send(searchUsersQueryRequest);
        request.setAttribute("users", users);

        request.getRequestDispatcher("admin_user_management.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
