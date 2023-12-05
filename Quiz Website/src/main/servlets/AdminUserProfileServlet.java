package main.servlets;

import main.application.HandlerProvider;
import main.application.commands.user.AddFriendRequestCommand.AddFriendRequestCommandRequest;
import main.application.commands.user.RemoveUserCommand.RemoveUserCommandRequest;
import main.application.commands.user.RemoveUserCommand.RemoveUserCommandResponse;
import main.application.queries.quiz.QuizesResponse;
import main.application.queries.quiz.UserAttemptedQuizes.UserAttemptedQuizesQuery;
import main.application.queries.quiz.UserCreatedQuizes.UserCreatedQuizesQuery;
import main.application.queries.user.GetFriendshipsQuery.GetFriendshipsQueryRequest;
import main.application.queries.user.GetUserQuery.UserQueryResponse;
import main.domain.User;
import main.domain.enums.UserStatus;
import main.mediator.Mediator;
import main.persistence.DbContext;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/adminUserProfile")
public class AdminUserProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userStatus = JWTHelper.getDataFromToken((String) request.getSession().getAttribute("Authorization")).role();

        if (!userStatus.equals("ADMIN")) {
            throw new RuntimeException("Unauthorized access");
        }

        Mediator mediator = new Mediator(new HandlerProvider(new DbContext()));

        String profileUsername = request.getParameter("username");
        request.setAttribute("profileUsername", profileUsername);

        List<QuizesResponse> createdByUser = mediator.send(new UserCreatedQuizesQuery(profileUsername));
        request.setAttribute("createdByUser", createdByUser);

        GetFriendshipsQueryRequest usersFriends = new GetFriendshipsQueryRequest();
        usersFriends.setUser(profileUsername);
        List<String> friends = mediator.send(usersFriends).getFriends();
        request.setAttribute("friends", friends);

        List<QuizesResponse> takenByUser = mediator.send(new UserAttemptedQuizesQuery(profileUsername));
        request.setAttribute("takenByUser", takenByUser);


        request.getRequestDispatcher("admin_profile_page.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userStatus = JWTHelper.getDataFromToken((String) request.getSession().getAttribute("Authorization")).role();

        if (!userStatus.equals("ADMIN")) {
            throw new RuntimeException("Unauthorized access");
        }

        Mediator mediator = new Mediator(new HandlerProvider(new DbContext()));
        String username = request.getParameter("username");

        RemoveUserCommandResponse removeUserCommandResponse = mediator.send(new RemoveUserCommandRequest(username));

        request.getRequestDispatcher("adminSearchUsers").forward(request, response);
    }
}
