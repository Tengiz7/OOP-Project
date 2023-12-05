package main.servlets;

import main.application.HandlerProvider;
import main.application.queries.quiz.GetQuizStatistics.QuizStatisticsQuery;
import main.application.queries.quiz.GetQuizStatistics.QuizStatisticsQueryResponse;
import main.application.queries.quiz.GetTopPerformanceForQuiz.TopPerformanceForQuizQuery;
import main.application.queries.quiz.GetTopPerformanceForQuiz.TopPerformanceForQuizQueryResponse;
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

@WebServlet("/quizSummary")
public class QuizSummaryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Mediator mediator = new Mediator(new HandlerProvider(new DbContext()));

        HttpSession session = request.getSession();
        String username = JWTHelper.getDataFromToken(session.getAttribute("Authorization").toString()).username();

        int quizId = Integer.parseInt(request.getParameter("quizId"));

        QuizByIdResponse quiz = mediator.send(new QuizByIdQuery(quizId));
        request.setAttribute("quiz", quiz);

        QuizStatisticsQueryResponse quizStats = mediator.send(new QuizStatisticsQuery(quizId));
        request.setAttribute("quizStats", quizStats);

        List<TopPerformanceForQuizQueryResponse> topPerformaces = mediator.send(new TopPerformanceForQuizQuery(quizId));
        request.setAttribute("topPerformaces", topPerformaces);

        GetUserPastPerformanceQuery myPastPerformaceRequest = new GetUserPastPerformanceQuery();
        myPastPerformaceRequest.setUsername(username);
        myPastPerformaceRequest.setQuizId(quizId);
        List<GetUserPastPerformanceResponse> myPastPerformances = mediator.send(myPastPerformaceRequest);
        request.setAttribute("myPastPerformances", myPastPerformances);

        request.setAttribute("quiz", quiz);

        request.getRequestDispatcher("quiz/quiz_summary_page.jsp").forward(request, response);
    }
}
