package main.servlets;

import main.application.HandlerProvider;
import main.application.commands.user.AddUserComand.AddUserCommandRequest;
import main.mediator.Mediator;
import main.persistence.DbContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/sign-up")
public class UserSignUpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("sign_up.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Mediator mediator = new Mediator(new HandlerProvider(new DbContext()));
        AddUserCommandRequest request = new AddUserCommandRequest();
        request.setUsername(req.getParameter("username"));
        request.setPassword(req.getParameter("password"));
        mediator.send(request);
        req.getRequestDispatcher("sign_in.jsp").forward(req, resp);
    }
}
