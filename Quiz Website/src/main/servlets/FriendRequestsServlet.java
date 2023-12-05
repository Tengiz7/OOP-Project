package main.servlets;

import main.application.HandlerProvider;
import main.application.commands.user.AddFriendshipCommand.AddFriendshipCommandRequest;
import main.application.commands.user.AddFriendshipCommand.AddFriendshipCommandResponse;
import main.application.commands.user.RemoveFriendRequestCommand.RemoveFriendRequestCommandRequest;
import main.application.commands.user.RemoveFriendRequestCommand.RemoveFriendRequestCommandResponse;
import main.application.queries.user.GetReceivedFriendRequestsQuery.ReceivedFriendRequestsQueryRequest;
import main.application.queries.user.GetReceivedFriendRequestsQuery.ReceivedFriendRequestsQueryResponse;
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

@WebServlet("/friendRequests")
public class FriendRequestsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = JWTHelper.getDataFromToken((String) session.getAttribute("Authorization")).username();

        Mediator mediator = new Mediator(new HandlerProvider(new DbContext()));

        ReceivedFriendRequestsQueryRequest receivedFriendRequestsQueryRequest = new ReceivedFriendRequestsQueryRequest();
        receivedFriendRequestsQueryRequest.setReceiverUsername(username);
        ReceivedFriendRequestsQueryResponse receivedRequests = mediator.send(receivedFriendRequestsQueryRequest);
        request.setAttribute("receivedRequests", receivedRequests);

        request.getRequestDispatcher("friend_requests.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = JWTHelper.getDataFromToken((String) session.getAttribute("Authorization")).username();

        Mediator mediator = new Mediator(new HandlerProvider(new DbContext()));
        String action = request.getParameter("action");
        String requestingUsername = request.getParameter("requestingUsername");

        if (action != null && !action.isEmpty()) {
            if (action.equals("accept")) {
                AddFriendshipCommandRequest addFriendshipCommandRequest = new AddFriendshipCommandRequest();
                addFriendshipCommandRequest.setUser(username);
                addFriendshipCommandRequest.setFutureFriend(requestingUsername);
                addFriendshipCommandRequest.setLocalDateTime(LocalDateTime.now());
                AddFriendshipCommandResponse addFriendshipCommandResponse = mediator.send(addFriendshipCommandRequest);

            } else if (action.equals("remove")) {
                RemoveFriendRequestCommandRequest removeFriendRequestCommandRequest = new RemoveFriendRequestCommandRequest();
                removeFriendRequestCommandRequest.setSenderUsername(requestingUsername);
                removeFriendRequestCommandRequest.setReceiverUsername(username);
                RemoveFriendRequestCommandResponse removeFriendRequestCommandResponse = mediator.send(removeFriendRequestCommandRequest);
            }
        }
        doGet(request, response);
    }

}
