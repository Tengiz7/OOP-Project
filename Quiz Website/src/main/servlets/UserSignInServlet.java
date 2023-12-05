package main.servlets;

import main.application.HandlerProvider;
import main.application.queries.user.GetUserQuery.UserQueryRequest;
import main.application.queries.user.GetUserQuery.UserQueryResponse;
import main.mediator.Mediator;
import main.persistence.DbContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/sign-in")
public class UserSignInServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Mediator mediator = new Mediator(new HandlerProvider(new DbContext()));
        UserQueryRequest request = new UserQueryRequest();
        request.setUsername(req.getParameter("username"));
        request.setPassword(req.getParameter("password"));
        try {
            UserQueryResponse response = mediator.send(request);
            JWTHelper.setBearerToken(req, new JWTHelper.UserData(response.getUsername(), response.getUserStatus().toString()));
            req.getRequestDispatcher("../landing.jsp").forward(req, resp);
        } catch (RuntimeException e) {
            req.setAttribute("errorMessage", "Incorrect username or password");
            req.getRequestDispatcher("sign_in.jsp").forward(req, resp);
        }
    }
}
