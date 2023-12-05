package main.servlets;

import main.application.HandlerProvider;
import main.application.queries.user.MessageQuery.MessageQueryRequest;
import main.application.queries.user.MessageQuery.MessageQueryResponse;
import main.application.queries.user.ReceivedMessagesQuery.ReceivedMessagesQueryResponse;
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

@WebServlet("/messageDetail")
public class MessageDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = JWTHelper.getDataFromToken((String) session.getAttribute("Authorization")).username();

        int messageId = Integer.parseInt(request.getParameter("messageId"));

        Mediator mediator = new Mediator(new HandlerProvider(new DbContext()));

        MessageQueryResponse message = mediator.send(new MessageQueryRequest(messageId));
        request.setAttribute("message", message);

        request.getRequestDispatcher("message_detail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private Message fetchMessage() {
        return new Message("Jane Smith", "John Doe", "Lorem ipsum dolor sit amet, consectetur adipiscing elit Lorem ipsum dolor sit amet, consectetur adipiscing elit Lorem ipsum dolor sit amet, consectetur adipiscing elit Lorem ipsum dolor sit amet, consectetur adipiscing elit", LocalDateTime.now(), 1);
    }
}
