package main.servlets;

import main.application.HandlerProvider;
import main.application.queries.quiz.GetUserPastPerformance.GetUserPastPerformanceQuery;
import main.application.queries.quiz.GetUserPastPerformance.GetUserPastPerformanceResponse;
import main.application.queries.quiz.QuizById.QuizByIdQuery;
import main.application.queries.quiz.QuizById.QuizByIdResponse;
import main.mediator.Mediator;
import main.persistence.DbContext;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/quizResult")
public class QuizResultServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Mediator mediator = new Mediator(new HandlerProvider(new DbContext()));

        int quizId = Integer.parseInt(request.getParameter("quizId"));

        String username = JWTHelper.getDataFromToken((String) request.getSession().getAttribute("Authorization")).username();

        QuizByIdResponse quiz = mediator.send(new QuizByIdQuery(quizId));
        request.setAttribute("quiz", quiz);

        GetUserPastPerformanceQuery myPastPerformaceRequest = new GetUserPastPerformanceQuery();
        myPastPerformaceRequest.setUsername(username);
        myPastPerformaceRequest.setQuizId(quizId);
        List<GetUserPastPerformanceResponse> myPastPerformances = mediator.send(myPastPerformaceRequest);
        request.setAttribute("myPastPerformances", myPastPerformances);

        request.getRequestDispatcher("quiz_result_page.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
