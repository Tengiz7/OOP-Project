package main.servlets;

import main.application.HandlerProvider;
import main.application.queries.user.ChallengeQuery.ChallengeQueryRequest;
import main.application.queries.user.ChallengeQuery.ChallengeQueryResponse;
import main.domain.Challenge;
import main.domain.Message;
import main.mediator.Mediator;
import main.persistence.DbContext;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/challengeDetail")
public class ChallengeDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = JWTHelper.getDataFromToken((String) session.getAttribute("Authorization")).username();

        int challengeId = Integer.parseInt(request.getParameter("challengeId"));

        Mediator mediator = new Mediator(new HandlerProvider(new DbContext()));

        ChallengeQueryResponse challenge = mediator.send(new ChallengeQueryRequest(challengeId));
        request.setAttribute("challenge", challenge);

        request.getRequestDispatcher("challenge_detail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
