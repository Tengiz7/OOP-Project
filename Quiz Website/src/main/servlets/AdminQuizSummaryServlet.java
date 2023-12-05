package main.servlets;

import main.application.HandlerProvider;
import main.application.commands.quiz.DeleteHistory.DeleteHistoryCommand;
import main.application.commands.quiz.DeleteHistory.DeleteHistoryResponse;
import main.application.commands.quiz.DeleteQuiz.DeleteQuizCommand;
import main.application.commands.quiz.DeleteQuiz.DeleteQuizResponse;
import main.application.queries.quiz.GetQuizStatistics.QuizStatisticsQuery;
import main.application.queries.quiz.GetQuizStatistics.QuizStatisticsQueryResponse;
import main.application.queries.quiz.GetTopPerformanceForQuiz.TopPerformanceForQuizQuery;
import main.application.queries.quiz.GetTopPerformanceForQuiz.TopPerformanceForQuizQueryResponse;
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

@WebServlet("/adminQuizSummary")
public class AdminQuizSummaryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Mediator mediator = new Mediator(new HandlerProvider(new DbContext()));

        String userStatus = JWTHelper.getDataFromToken((String) request.getSession().getAttribute("Authorization")).role();

        if (!userStatus.equals("ADMIN")) {
            throw new RuntimeException("Unauthorized access");
        }

        int quizId = Integer.parseInt(request.getParameter("quizId"));

        QuizByIdResponse quiz = mediator.send(new QuizByIdQuery(quizId));
        request.setAttribute("quiz", quiz);

        QuizStatisticsQueryResponse quizStats = mediator.send(new QuizStatisticsQuery(quizId));
        request.setAttribute("quizStats", quizStats);

        List<TopPerformanceForQuizQueryResponse> topPerformaces = mediator.send(new TopPerformanceForQuizQuery(quizId));
        request.setAttribute("topPerformaces", topPerformaces);

        request.getRequestDispatcher("admin_quiz_summary_page.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userStatus = JWTHelper.getDataFromToken((String) request.getSession().getAttribute("Authorization")).role();

        if (!userStatus.equals("ADMIN")) {
            throw new RuntimeException("Unauthorized access");
        }

        Mediator mediator = new Mediator(new HandlerProvider(new DbContext()));
        String action = request.getParameter("action");
        int quizId = Integer.parseInt(request.getParameter("currentQuizId"));

        if (action != null && !action.isEmpty()) {
            if (action.equals("clearHistory")) {
                DeleteHistoryCommand deleteHistoryCommand = new DeleteHistoryCommand();
                deleteHistoryCommand.setId(quizId);
                DeleteHistoryResponse deleteHistoryCommandResponse = mediator.send(deleteHistoryCommand);
            } else if (action.equals("deleteQuiz")) {
                DeleteQuizCommand deleteQuizCommand = new DeleteQuizCommand();
                deleteQuizCommand.setId(quizId);
                DeleteQuizResponse deleteHistoryCommandResponse = mediator.send(deleteQuizCommand);
            }
        }
        request.getRequestDispatcher("adminIndex").forward(request, response);
    }


}
