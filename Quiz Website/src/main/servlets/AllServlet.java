package main.servlets;

import main.application.HandlerProvider;
import main.application.queries.quiz.FriendAttemptedQuizes.FriendAttemptedQuizesQuery;
import main.application.queries.quiz.FriendAttemptedQuizes.FriendAttemptedQuizesResponse;
import main.application.queries.quiz.FriendCreatedQuizes.FriendCreatedQuizesQuery;
import main.application.queries.quiz.NewQuizes.NewQuizesQuery;
import main.application.queries.quiz.NewQuizes.NewQuizesResponse;
import main.application.queries.quiz.QuizesResponse;
import main.application.queries.quiz.UserAttemptedQuizes.UserAttemptedQuizesQuery;
import main.application.queries.quiz.UserCreatedQuizes.UserCreatedQuizesQuery;
import main.application.queries.user.GetFriendshipsQuery.GetFriendshipsQueryRequest;
import main.domain.User;
import main.mediator.Mediator;
import main.persistence.DbContext;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/all")
public class AllServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Mediator mediator = new Mediator(new HandlerProvider(new DbContext()));
        HttpSession session = request.getSession();

        String username = JWTHelper.getDataFromToken((String) session.getAttribute("Authorization")).username();
        request.setAttribute("username", username);

        String filter = request.getParameter("filter");
        request.setAttribute("filter", filter);

        switch (filter) {
            case "allQuizzes":
                List<NewQuizesResponse> allQuizzes = mediator.send(new NewQuizesQuery());
                request.setAttribute("allQuizzes", allQuizzes);
                break;

            case "allFriends":
                GetFriendshipsQueryRequest usersFriends = new GetFriendshipsQueryRequest();
                usersFriends.setUser(username);
                List<String> friends = mediator.send(usersFriends).getFriends();
                request.setAttribute("allFriends", friends);

                break;

            case "takenByMe":
                List<QuizesResponse> takenByMe = mediator.send(new UserAttemptedQuizesQuery(username));
                request.setAttribute("takenByMe", takenByMe);

                break;

            case "takenByFriends":
                List<FriendAttemptedQuizesResponse> takenByFriends = mediator.send(new FriendAttemptedQuizesQuery(username));
                request.setAttribute("takenByFriends", takenByFriends);

                break;

            case "createdByMe":
                List<QuizesResponse> createdByMe = mediator.send(new UserCreatedQuizesQuery(username));
                request.setAttribute("createdByMe", createdByMe);

                break;

            case "createdByFriends":
                List<QuizesResponse> createdByFriends = mediator.send(new FriendCreatedQuizesQuery(username));
                request.setAttribute("createdByFriends", createdByFriends);

                break;

            default:
                List<NewQuizesResponse> defaultQuizzes = mediator.send(new NewQuizesQuery());
                request.setAttribute("defaultQuizzes", defaultQuizzes);

                break;
        }

        request.getRequestDispatcher("all_quizzes_or_friends.jsp").forward(request, response);
    }
}
