package main.servlets;

import main.application.HandlerProvider;
import main.application.queries.quiz.QuizesResponse;
import main.application.queries.quiz.UserAttemptedQuizes.UserAttemptedQuizesQuery;
import main.application.queries.quiz.UserCreatedQuizes.UserCreatedQuizesQuery;
import main.application.queries.user.FriendshipExistsQuery.FriendshipExistsQueryRequest;
import main.application.queries.user.FriendshipExistsQuery.FriendshipExistsQueryResponse;
import main.application.queries.user.GetFriendshipsQuery.GetFriendshipsQueryRequest;
import main.application.queries.user.GetUserQuery.UserQueryRequest;
import main.application.queries.user.GetUserQuery.UserQueryResponse;
import main.application.queries.user.UsernameExistsQuery.UsernameExistsQueryRequest;
import main.application.queries.user.UsernameExistsQuery.UsernameExistsQueryResponse;
import main.application.queries.user.isFriendRequestSent.IsFriendRequestSentRequest;
import main.application.queries.user.isFriendRequestSent.IsFriendRequestSentResponse;
import main.domain.User;
import main.domain.enums.UserStatus;
import main.mediator.Mediator;
import main.mediator.abstractions.IMediator;
import main.persistence.DbContext;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/user/profile")
public class
UserProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Mediator mediator = new Mediator(new HandlerProvider(new DbContext()));
        String visitingUsername = JWTHelper.getDataFromToken((String) request.getSession().getAttribute("Authorization")).username();

        request.setAttribute("visitingUsername", visitingUsername);

        String profileUsername = request.getParameter("username");
        request.setAttribute("profileUsername", profileUsername);

        UsernameExistsQueryRequest usernameExists = new UsernameExistsQueryRequest();
        usernameExists.setUsername(profileUsername);
        UsernameExistsQueryResponse userResponse = mediator.send(usernameExists);
        if (!userResponse.getExists())
            throw new RuntimeException("User does not exist");
        request.setAttribute("userResponse", profileUsername);

        IsFriendRequestSentRequest sentOrNot = new IsFriendRequestSentRequest();
        sentOrNot.setSender(visitingUsername);
        sentOrNot.setReceiver(profileUsername);
        IsFriendRequestSentResponse isFriendRequestSent = mediator.send(sentOrNot);
        request.setAttribute("isFriendRequestSent", isFriendRequestSent.getExists());

        FriendshipExistsQueryRequest friendshipExists = new FriendshipExistsQueryRequest();
        friendshipExists.setUser_1(visitingUsername);
        friendshipExists.setUser_2(profileUsername);
        FriendshipExistsQueryResponse areFriends = mediator.send(friendshipExists);
        request.setAttribute("areFriends", areFriends.getExists());

        List<QuizesResponse> createdByUser = mediator.send(new UserCreatedQuizesQuery(profileUsername));
        request.setAttribute("createdByUser", createdByUser);

        GetFriendshipsQueryRequest usersFriends = new GetFriendshipsQueryRequest();
        usersFriends.setUser(profileUsername);
        List<String> friends = mediator.send(usersFriends).getFriends();
        request.setAttribute("friends", friends);

        List<QuizesResponse> takenByUser = mediator.send(new UserAttemptedQuizesQuery(profileUsername));
        request.setAttribute("takenByUser", takenByUser);


        request.getRequestDispatcher("profile_page.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

}
