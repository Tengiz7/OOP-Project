package main.servlets;

import main.application.HandlerProvider;
import main.application.queries.admin.AllAnnouncements.AllAnnouncementsResponse;
import main.application.queries.user.ReceivedMessagesQuery.ReceivedMessagesQueryRequest;
import main.application.queries.user.ReceivedMessagesQuery.ReceivedMessagesQueryResponse;
import main.domain.Message;
import main.mediator.Mediator;
import main.mediator.abstractions.IMediator;
import main.persistence.DbContext;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/messageInbox")
public class MessageInboxServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = JWTHelper.getDataFromToken((String) session.getAttribute("Authorization")).username();

        Mediator mediator = new Mediator(new HandlerProvider(new DbContext()));
        ReceivedMessagesQueryRequest receivedMessagesQueryRequest = new ReceivedMessagesQueryRequest();
        receivedMessagesQueryRequest.setReceiverUsername(username);
        ReceivedMessagesQueryResponse receivedMessages = mediator.send(receivedMessagesQueryRequest);
        request.setAttribute("receivedMessages", receivedMessages);



        request.getRequestDispatcher("messages_inbox.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
