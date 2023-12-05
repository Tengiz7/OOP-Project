package main.servlets;

import main.application.HandlerProvider;
import main.application.queries.quiz.NewQuizes.NewQuizesQuery;
import main.application.queries.quiz.NewQuizes.NewQuizesResponse;
import main.application.queries.quiz.PopularQuizes.PopularQuizesQuery;
import main.application.queries.quiz.QuizesResponse;
import main.application.queries.quiz.UserAttemptedQuizes.UserAttemptedQuizesQuery;
import main.mediator.Mediator;
import main.persistence.DbContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("")
public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getSession().getAttribute("Authorization") == null) {
            resp.sendRedirect("user/sign_in.jsp");
            return;
        }
        HttpSession session = req.getSession();
        String username = JWTHelper.getDataFromToken((String) session.getAttribute("Authorization")).username();

        Mediator mediator = new Mediator(new HandlerProvider(new DbContext()));

        List<QuizesResponse> popularQuizzes = mediator.send(new PopularQuizesQuery());
        req.setAttribute("popularQuizzes", popularQuizzes);

        List<QuizesResponse> takenByMe = mediator.send(new UserAttemptedQuizesQuery(username));
        req.setAttribute("takenByMe", takenByMe);

        req.getRequestDispatcher("/landing.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
