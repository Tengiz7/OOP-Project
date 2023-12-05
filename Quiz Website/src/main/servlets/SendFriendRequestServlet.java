package main.servlets;

import main.application.HandlerProvider;
import main.application.commands.user.AddFriendRequestCommand.AddFriendRequestCommandRequest;
import main.application.commands.user.AddFriendRequestCommand.AddFriendRequestCommandResponse;
import main.application.queries.user.GetUserQuery.UserQueryResponse;
import main.mediator.Mediator;
import main.persistence.DbContext;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/user/sendFriendRequest")
public class SendFriendRequestServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Mediator mediator = new Mediator(new HandlerProvider(new DbContext()));
        String visitingUsername = JWTHelper.getDataFromToken((String) request.getSession().getAttribute("Authorization")).username();
        String profileUsername = request.getParameter("profileUsername");

        AddFriendRequestCommandRequest sendFriendRequest = new AddFriendRequestCommandRequest();
        sendFriendRequest.setSenderUsername(visitingUsername);
        sendFriendRequest.setReceiverUsername(profileUsername);
        AddFriendRequestCommandResponse send = mediator.send(sendFriendRequest);

        request.getRequestDispatcher("profile?username=" + profileUsername).forward(request, response);
    }
}
