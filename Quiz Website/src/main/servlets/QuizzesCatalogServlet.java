package main.servlets;

import main.application.HandlerProvider;
import main.application.queries.quiz.FriendAttemptedQuizes.FriendAttemptedQuizesResponse;
import main.application.queries.quiz.NewQuizes.NewQuizesQuery;
import main.application.queries.quiz.NewQuizes.NewQuizesResponse;
import main.application.queries.quiz.PopularQuizes.PopularQuizesQuery;
import main.application.queries.quiz.QuizesResponse;
import main.application.queries.quiz.UserAttemptedQuizes.UserAttemptedQuizesQuery;
import main.application.queries.quiz.UserCreatedQuizes.UserCreatedQuizesQuery;
import main.domain.User;
import main.mediator.Mediator;
import main.persistence.DbContext;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/quizzesCatalog")
public class QuizzesCatalogServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = JWTHelper.getDataFromToken((String) session.getAttribute("Authorization")).username();

        Mediator mediator = new Mediator(new HandlerProvider(new DbContext()));

        List<QuizesResponse> popularQuizzes = mediator.send(new PopularQuizesQuery());
        request.setAttribute("popularQuizzes", popularQuizzes);

        List<NewQuizesResponse> newQuizzes = mediator.send(new NewQuizesQuery());
        request.setAttribute("newQuizzes", newQuizzes);

        List<QuizesResponse> takenByMe = mediator.send(new UserAttemptedQuizesQuery(username));
        request.setAttribute("takenByMe", takenByMe);

        List<QuizesResponse> createdByMe = mediator.send(new UserCreatedQuizesQuery(username));
        request.setAttribute("createdByMe", createdByMe);

        request.getRequestDispatcher("quizzes_catalog_page.jsp").forward(request, response);
    }

}
