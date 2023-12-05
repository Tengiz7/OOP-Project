package main.servlets;

import main.application.HandlerProvider;
import main.application.commands.user.SendChallengeCommand.SendChallengeCommandRequest;
import main.application.commands.user.SendChallengeCommand.SendChallengeCommandResponse;
import main.mediator.Mediator;
import main.persistence.DbContext;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/sendChallenge")
public class SendChallengeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("send_challenge.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Mediator mediator = new Mediator(new HandlerProvider(new DbContext()));
        HttpSession session = request.getSession();
        String username = JWTHelper.getDataFromToken((String) session.getAttribute("Authorization")).username();

        String messageText = request.getParameter("message");
        String receiver = request.getParameter("receiver");
        int quizId = Integer.parseInt(request.getParameter("quizId"));

        SendChallengeCommandRequest sendChallengeCommandRequest = new SendChallengeCommandRequest();
        sendChallengeCommandRequest.setMessage(messageText);
        sendChallengeCommandRequest.setReceiverName(receiver);
        sendChallengeCommandRequest.setSenderName(username);
        sendChallengeCommandRequest.setLocalDateTime(LocalDateTime.now());
        sendChallengeCommandRequest.setQuizID(quizId);
        SendChallengeCommandResponse challengeCommandResponse = mediator.send(sendChallengeCommandRequest);

        request.getRequestDispatcher("friendsActivity").forward(request, response);
    }
}
