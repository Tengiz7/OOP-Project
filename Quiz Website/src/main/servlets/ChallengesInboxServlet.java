package main.servlets;

import main.application.HandlerProvider;
import main.application.queries.user.ReceivedChallengesQuery.ReceivedChallengesQueryRequest;
import main.application.queries.user.ReceivedChallengesQuery.ReceivedChallengesQueryResponse;
import main.application.queries.user.ReceivedMessagesQuery.ReceivedMessagesQueryResponse;
import main.domain.Challenge;
import main.domain.Message;
import main.mediator.Mediator;
import main.persistence.DbContext;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/challengeInbox")
public class ChallengesInboxServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = JWTHelper.getDataFromToken((String) session.getAttribute("Authorization")).username();

        Mediator mediator = new Mediator(new HandlerProvider(new DbContext()));

        ReceivedChallengesQueryRequest receivedChallengesQueryRequest = new ReceivedChallengesQueryRequest();
        receivedChallengesQueryRequest.setReceiverUsername(username);
        ReceivedChallengesQueryResponse receivedChallenges = mediator.send(receivedChallengesQueryRequest);
        request.setAttribute("receivedChallenges", receivedChallenges);

        request.getRequestDispatcher("challenges_inbox.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
