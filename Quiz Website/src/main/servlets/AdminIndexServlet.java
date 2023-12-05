package main.servlets;

import main.application.HandlerProvider;
import main.application.queries.admin.Statistics.StatisticsQuery;
import main.application.queries.admin.Statistics.StatisticsReponse;
import main.application.queries.quiz.PopularQuizes.PopularQuizesQuery;
import main.application.queries.quiz.QuizesResponse;
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

@WebServlet("/adminIndex")
public class AdminIndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        JWTHelper.UserData userData = JWTHelper.getDataFromToken((String) session.getAttribute("Authorization"));
        String username = userData.username();

        if (!userData.role().equals(UserStatus.ADMIN.toString())) {
            throw new RuntimeException("Unauthorized access");
        }

        Mediator mediator = new Mediator(new HandlerProvider(new DbContext()));

        List<QuizesResponse> popularQuizzes = mediator.send(new PopularQuizesQuery());
        request.setAttribute("popularQuizzes", popularQuizzes);

        StatisticsReponse statistics = mediator.send(new StatisticsQuery());
        request.setAttribute("statistics", statistics);

        request.getRequestDispatcher("admin_home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

}
