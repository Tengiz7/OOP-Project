package main.servlets;

import main.application.HandlerProvider;
import main.application.queries.quiz.QuizById.QuizByIdQuery;
import main.application.queries.quiz.QuizById.QuizByIdResponse;
import main.mediator.Mediator;
import main.persistence.DbContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/quiz/quiz-test")
public class QuizTestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int quizId = Integer.parseInt(req.getParameter("id"));
        Mediator mediator = new Mediator(new HandlerProvider(new DbContext()));
        QuizByIdResponse response = mediator.send(new QuizByIdQuery(quizId));

        req.setAttribute("currentQuiz", response);
        req.getRequestDispatcher("quiz-test.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
