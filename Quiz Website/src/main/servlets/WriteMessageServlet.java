package main.servlets;

import main.application.HandlerProvider;
import main.application.commands.user.AddMessageCommand.AddMessageCommandRequest;
import main.application.commands.user.AddMessageCommand.AddMessageCommandResponse;
import main.mediator.Mediator;
import main.persistence.DbContext;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/writeMessage")
public class WriteMessageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("write_message.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Mediator mediator = new Mediator(new HandlerProvider(new DbContext()));
        HttpSession session = request.getSession();
        String username = JWTHelper.getDataFromToken((String) session.getAttribute("Authorization")).username();

        String receiver = request.getParameter("receiver");
        String messageText = request.getParameter("message");

        AddMessageCommandRequest sendMessageRequest = new AddMessageCommandRequest();
        sendMessageRequest.setMessage(messageText);
        sendMessageRequest.setReceiverUsername(receiver);
        sendMessageRequest.setSenderUsername(username);
        sendMessageRequest.setLocalDateTime(LocalDateTime.now());
        AddMessageCommandResponse sendMessage = mediator.send(sendMessageRequest);

        request.getRequestDispatcher("messageInbox").forward(request, response);
    }
}
