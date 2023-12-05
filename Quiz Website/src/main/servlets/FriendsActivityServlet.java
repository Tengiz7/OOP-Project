package main.servlets;

import main.application.HandlerProvider;
import main.application.queries.quiz.FriendAttemptedQuizes.FriendAttemptedQuizesQuery;
import main.application.queries.quiz.FriendAttemptedQuizes.FriendAttemptedQuizesResponse;
import main.application.queries.quiz.FriendCreatedQuizes.FriendCreatedQuizesQuery;
import main.application.queries.quiz.QuizesResponse;
import main.application.queries.user.GetFriendshipsQuery.GetFriendshipsQueryRequest;
import main.application.queries.user.GetFriendshipsQuery.GetFriendshipsQueryResponse;
import main.domain.User;
import main.mediator.Mediator;
import main.persistence.DbContext;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/friendsActivity")
public class FriendsActivityServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Mediator mediator = new Mediator(new HandlerProvider(new DbContext()));
        HttpSession session = request.getSession();
        String username = JWTHelper.getDataFromToken((String) session.getAttribute("Authorization")).username();
        request.setAttribute("username", username);

        GetFriendshipsQueryRequest usersFriends = new GetFriendshipsQueryRequest();
        usersFriends.setUser(username);
        List<String> friends = mediator.send(usersFriends).getFriends();
        request.setAttribute("friends", friends);

        List<FriendAttemptedQuizesResponse> attemptedByFriends = mediator.send(new FriendAttemptedQuizesQuery(username));
        request.setAttribute("attemptedByFriends", attemptedByFriends);

        List<QuizesResponse> createdByFriends = mediator.send(new FriendCreatedQuizesQuery(username));
        request.setAttribute("createdByFriends", createdByFriends);

        request.getRequestDispatcher("friends_activity_page.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
